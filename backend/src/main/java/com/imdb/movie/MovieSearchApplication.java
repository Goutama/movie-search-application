package com.imdb.movie;

import com.imdb.movie.config.ApplicationProperties;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Initializes the movie search application.
 *
 * @author gbhat on 14/05/2020.
 */
@EnableBatchProcessing
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication(scanBasePackages = {"com.imdb.movie.*"})
public class MovieSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieSearchApplication.class, args);
	}
}
