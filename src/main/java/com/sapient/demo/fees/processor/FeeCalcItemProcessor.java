package com.sapient.demo.fees.processor;

import com.sapient.demo.exception.FeeCalcTransException;
import com.sapient.demo.fees.data.FeeCalcDetails;
import com.sapient.demo.fees.data.TransactionDetails;
import com.sapient.demo.fees.service.FeeCalcServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class FeeCalcItemProcessor implements ItemProcessor<TransactionDetails, FeeCalcDetails> {

    private static final Logger log = LoggerFactory.getLogger(FeeCalcItemProcessor.class);

    @Autowired
    public FeeCalcServiceProvider feeCalcService;

    @Override
    public FeeCalcDetails process(final TransactionDetails item) throws Exception {
        try {
            return feeCalcService.process(item);
        } catch (FeeCalcTransException e){
            log.error("Error occurred {}",e);
        }
        return null;
    }

}
