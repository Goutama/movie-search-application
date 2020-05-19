package com.imdb.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * A SwaggerConfiguration.
 *
 * @author gbhat on 14/05/2020.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * The api info to be shown in the Swagger page
     *
     * @return the api info to be shown in the Swagger page
     */
    private static ApiInfo apiInformation() {
            return new ApiInfoBuilder()
                    .title("Movie Search Application")
                    .description("Following documentation is for the Movie Search Application REST API")
                    .version("v1.0")
                    .contact(new Contact("Goutama Bhat", "", "goutam.bht@gmail.com"))
                    .build();
    }

    /**
     * Returns the swagger configuration
     *
     * @return the swagger configuration
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(SwaggerConfiguration.apiInformation());
    }
}
