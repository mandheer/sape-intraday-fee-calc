package com.sapient.demo.fees.component;

import com.sapient.demo.logger.TransLogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class FeeCalcStatusListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(FeeCalcStatusListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(TransLogMessage.TRANS00001I,jobExecution.getCreateTime());
        log.info(TransLogMessage.TRANS00002I,jobExecution.getEndTime());
        super.afterJob(jobExecution);
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("Job completed successfully");
        }
    }
}
