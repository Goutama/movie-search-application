package com.imdb.movie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * A TypeCastDTO.
 *
 * @author gbhat on 14/05/2020.
 */
@Value
@Builder
@JsonInclude(Include.NON_NULL)
public class TypeCastDTO {
    Boolean isTypeCasted;
    List<String> genres;
}
