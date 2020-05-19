package com.imdb.movie.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * @author gbhat on 14/05/2020.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    @NotNull
    private String host;
}
