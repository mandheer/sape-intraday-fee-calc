package com.sapient.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ApplicationRunner {

    public static final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    public static void main(String[] args) {
        log.info("Starting the application...");
        SpringApplication.run(ApplicationRunner.class,args);
        log.info("Application completed successfully...");
    }

  @Scheduled(cron = "0 0 0 */1 * ?")
    public void automatedRun() throws Exception {
        log.info("Starting the cron job...");
        jobLauncher.run(job,new JobParametersBuilder()
                .addString("JobID",String.valueOf(System.currentTimeMillis()))
                .toJobParameters());
        log.info("cron job triggered successfully");
    }
}
