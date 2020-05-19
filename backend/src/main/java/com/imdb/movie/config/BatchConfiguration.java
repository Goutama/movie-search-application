package com.imdb.movie.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

/**
 * A batch configuration.
 *
 * @author gbhat on 17/05/2020.
 */
@Configuration
public class BatchConfiguration {

    @Bean("concurrentJobLauncher")
    public JobLauncher jobLauncher(@Qualifier("taskExecutor") TaskExecutor taskExecutor, JobRepository jobRepository){
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setTaskExecutor(taskExecutor);
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }
}
