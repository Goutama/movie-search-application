package com.imdb.movie.batch.job;

import com.imdb.movie.batch.converter.GzipBufferedReader;
import com.imdb.movie.batch.processor.AkaProcessor;
import com.imdb.movie.batch.reader.AkaReader;
import com.imdb.movie.batch.writer.AkaWriter;
import com.imdb.movie.domain.Aka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * A job to process Aka details.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
@Slf4j
public class AkaJob extends JobExecutionListenerSupport {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final GzipBufferedReader gZipBufferedReader;

    private final AkaWriter writer;

    private final AkaProcessor processor;

    private final TaskExecutor taskExecutor;

    public AkaJob(JobBuilderFactory jobBuilderFactory,
                  StepBuilderFactory stepBuilderFactory,
                  GzipBufferedReader gZipBufferedReader,
                  AkaWriter writer,
                  AkaProcessor processor,
                  @Qualifier("taskExecutor") TaskExecutor taskExecutor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.gZipBufferedReader = gZipBufferedReader;
        this.writer = writer;
        this.processor = processor;
        this.taskExecutor = taskExecutor;
    }

    public Job akaJob() {

        Step step = stepBuilderFactory.get("aka-job-step")
                .<Aka, Aka> chunk(25)
                .reader(new AkaReader(new ClassPathResource("data/title.akas.tsv.gz"), gZipBufferedReader))
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .taskExecutor(taskExecutor)
                .build();

        return jobBuilderFactory.get("aka-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Batch job completed for Aka");
        }
    }

}
