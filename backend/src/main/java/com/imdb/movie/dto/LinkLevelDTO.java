package com.imdb.movie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

/**
 * A LinkLevelDTO.
 *
 * @author gbhat on 19/05/2020.
 */
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkLevelDTO {
    String sourceName;
    String targetName;
    Short levelOfSeparation;
}
