package com.example.Q5.batch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchJobRunner implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importCrimeJob;

    @Override
    public void run(String... args) throws Exception {
        JobExecution execution = jobLauncher.run(importCrimeJob, new JobParameters());
        System.out.println("STATUS :: " + execution.getStatus());
    }
}