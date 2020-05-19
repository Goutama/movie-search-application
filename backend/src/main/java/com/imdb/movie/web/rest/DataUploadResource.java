package com.imdb.movie.web.rest;

import com.imdb.movie.batch.job.BasicJob;
import com.imdb.movie.batch.job.NameJob;
import com.imdb.movie.batch.job.PrincipalJob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing data upload.
 *
 * @author gbhat on 16/05/2020.
 */
@Api(value = "Resource to handle data upload")
@RestController
@RequestMapping("/upload")
@Slf4j
public class DataUploadResource {

    private final JobLauncher jobLauncher;

    private final NameJob nameJob;

    private final BasicJob basicJob;

    private final PrincipalJob principalJob;

    public DataUploadResource(NameJob nameJob, BasicJob basicJob, PrincipalJob principalJob,
                              @Qualifier("concurrentJobLauncher") JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
        this.nameJob = nameJob;
        this.basicJob = basicJob;
        this.principalJob = principalJob;
    }

    /**
     * Method to trigger batch jobs.
     *
     * @return the message.
     * @throws Exception the job related exception.
     */
    @ApiOperation("End point to trigger batch jobs")
    @PutMapping("/run-batch-jobs")
    public String runBatchJobs() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters();

        jobLauncher.run(nameJob.nameJob(), jobParameters);
        jobLauncher.run(basicJob.basicJob(), jobParameters);
        jobLauncher.run(principalJob.principalJob(), jobParameters);

        return "Batch jobs are invoked";
    }
}
