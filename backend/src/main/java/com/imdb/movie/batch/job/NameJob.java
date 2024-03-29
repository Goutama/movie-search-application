package com.imdb.movie.batch.job;

import com.imdb.movie.batch.converter.GzipBufferedReader;
import com.imdb.movie.batch.processor.NameProcessor;
import com.imdb.movie.batch.reader.NameReader;
import com.imdb.movie.batch.writer.NameWriter;
import com.imdb.movie.config.ApplicationProperties;
import com.imdb.movie.domain.Name;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * A job to upload Name details.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
@Slf4j
public class NameJob extends JobExecutionListenerSupport {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final GzipBufferedReader gZipBufferedReader;
    private final NameWriter writer;
    private final NameProcessor processor;
    private final TaskExecutor taskExecutor;
    private final ApplicationProperties applicationProperties;

    public NameJob(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   GzipBufferedReader gZipBufferedReader,
                   NameWriter writer,
                   NameProcessor processor,
                   @Qualifier("taskExecutor") TaskExecutor taskExecutor,
                   ApplicationProperties applicationProperties) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.gZipBufferedReader = gZipBufferedReader;
        this.writer = writer;
        this.processor = processor;
        this.taskExecutor = taskExecutor;
        this.applicationProperties = applicationProperties;
    }

    public Job buildJob() {
        Step step =
                stepBuilderFactory
                        .get("name-job-step")
                        .<Name, Name>chunk(50)
                        .reader(
                                new NameReader(
                                        new ClassPathResource(applicationProperties.getBatchJobs().getNameFileDir()), gZipBufferedReader)
                        ).processor(processor)
                        .writer(writer)
                        .faultTolerant()
                        .skipLimit(100)
                        .skip(FlatFileParseException.class)
                        .taskExecutor(taskExecutor)
                        .build();

        return jobBuilderFactory
                .get("name-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Batch job completed for Name");
        }
    }

}
