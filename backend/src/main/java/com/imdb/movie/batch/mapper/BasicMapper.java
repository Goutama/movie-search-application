package com.imdb.movie.batch.mapper;

import com.imdb.movie.domain.Basic;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.Set;

import static com.imdb.movie.batch.util.FieldUtil.replaceWithNull;
import static java.util.Objects.nonNull;

/**
 * A field mapper for Basic.
 *
 * @author gbhat on 17/05/2020.
 */
public class BasicMapper implements FieldSetMapper<Basic> {

    @Override
    public Basic mapFieldSet(FieldSet fieldSet) {
        Basic basic = new Basic();
        basic.setTconst(fieldSet.readString("tconst"));
        basic.setTitleType(replaceWithNull(fieldSet, "titleType"));
        basic.setPrimaryTitle(replaceWithNull(fieldSet, "primaryTitle"));
        basic.setOriginalTitle(replaceWithNull(fieldSet, "originalTitle"));
        basic.setIsAdult(fieldSet.readBoolean("isAdult"));
        basic.setStartYear(replaceWithNull(fieldSet, "startYear"));
        basic.setEndYear(replaceWithNull(fieldSet, "endYear"));
        if (nonNull(replaceWithNull(fieldSet, "runtimeMinutes"))) {
            basic.setRuntimeMinutes(fieldSet.readInt("runtimeMinutes"));
        }
        if (nonNull(replaceWithNull(fieldSet, "genres"))) {
            basic.setGenres(Set.of(fieldSet.readString("genres").split(",")));
        }
        return basic;
    }


}
