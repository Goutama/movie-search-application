package com.imdb.movie.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * Properties specific to the application.
 *
 * @author gbhat on 14/05/2020.
 */
@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private BatchJobs batchJobs = new BatchJobs();

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "batch-jobs")
    public static class BatchJobs {
        @NotNull
        private String nameFileDir;
        @NotNull
        private String principalFileDir;
        @NotNull
        private String titleFileDir;
    }

}
