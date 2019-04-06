package com.sapient.demo.fees.configuration;

import com.sapient.demo.fees.cache.DataSetSingleton;
import com.sapient.demo.fees.component.FeeCalcStatusListener;
import com.sapient.demo.fees.data.FeeCalcDetails;
import com.sapient.demo.fees.data.TransactionDetails;
import com.sapient.demo.fees.processor.FeeCalcItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class FeeCalculatorConfig {

    public static final Logger LOG = LoggerFactory.getLogger(FeeCalculatorConfig.class);

    @Value("${input.sourceFile}")
    private Resource inputResource;

    @Value("${output.destinationFile}")
    private String outputResource;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importUserJob(FeeCalcStatusListener feeCalcStatusListener,Step step1){
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(feeCalcStatusListener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(FlatFileItemWriter<FeeCalcDetails> writer){
        return stepBuilderFactory.get("step1")
                .<TransactionDetails,FeeCalcDetails> chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .faultTolerant()
                .skipLimit(0)
                .build();
    }


    /**
     * configured reader here which has support for multiple file formats as defined by spring batch
     *
     * Flatfile reader is used for all types of files, only need to change is delimiter
     * @return
     */
    @Bean
    public FlatFileItemReader<TransactionDetails> reader(){
        return new FlatFileItemReaderBuilder<TransactionDetails>()
                .name("feeCalcItemReader")
                .resource(inputResource)
                .linesToSkip(1)
                .encoding("UTF-8")
                .strict(false)
                .delimited()
                .delimiter(",")
                .names(new String[]{"externalTransactionId","clientId", "securityId", "type", "transDate", "currentMktVal", "priority"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TransactionDetails>(){
                    {
                        setTargetType(TransactionDetails.class);

                    }
                })
                .build();
    }

    @Bean
    public FeeCalcItemProcessor processor() {
        return new FeeCalcItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<FeeCalcDetails> writer() {
        FlatFileItemWriter<FeeCalcDetails> flatFileItemWriter=
            new FlatFileItemWriterBuilder<FeeCalcDetails>()
                .name("feeCalcItemWriter")
                .resource(new FileSystemResource(outputResource))
                .lineAggregator(lineAggregator())
                .lineSeparator(System.lineSeparator())
                .headerCallback(new FlatFileHeaderCallback() {
                    @Override
                    public void writeHeader(Writer writer) throws IOException {
                        writer.write("------------Begin-----------"+System.lineSeparator());
                        writer.write("\"Client Id\",\"Transaction type\",\"Transaction Date\",\"Priority\",\"Processing Fee\""+System.lineSeparator());
                    }
                })
                .footerCallback(new FlatFileFooterCallback() {
                    @Override
                    public void writeFooter(Writer writer) throws IOException {
                        DataSetSingleton.getInstance().getData().forEach(d -> {
                            try {
                                writer.write(((FeeCalcDetails) d).getClientId() + "," +
                                        ((FeeCalcDetails) d).getType() + "," +
                                        ((FeeCalcDetails) d).getTransDate() + "," +
                                        ((FeeCalcDetails) d).getPriority() + "," +
                                        ((FeeCalcDetails) d).getProcessingFee()+
                                        System.lineSeparator());
                            } catch (IOException e ) {
                                LOG.warn("caught exception while writing to file");
                            }
                        });
                        writer.write("-----------END---------------");

                        DataSetSingleton.getInstance().invalidate();
                    }
                })
                .append(true)
                .build();
        return flatFileItemWriter;
    }

    @Bean
    public LineAggregator<FeeCalcDetails> lineAggregator(){
        return new DelimitedLineAggregator<FeeCalcDetails>(){
            {
               setDelimiter(",");
               setFieldExtractor(new BeanWrapperFieldExtractor<FeeCalcDetails>(){
                   {
                       setNames(new String[]{"clientId","type","transDate","priority","processingFee"});
                   }
               });
            }
        };
    }

    @Bean
    public ItemWriter<FeeCalcDetails> consoleWriter(){
        return new ItemWriter<FeeCalcDetails>() {
            @Override
            public void write(List<? extends FeeCalcDetails> items) throws Exception {
                items.forEach(feeCalc -> System.out.println(feeCalc));
            }
        };
    }

}
