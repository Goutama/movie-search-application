package com.imdb.movie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

/**
 * A CoincidenceDTO.
 *
 * @author gbhat on 19/05/2020.
 */
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoincidenceDTO {
    String sourceName;
    String targetName;
    Set<String> commonTitles;
}
