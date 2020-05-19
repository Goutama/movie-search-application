package com.imdb.movie.batch.mapper;

import com.imdb.movie.domain.Basic;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.Set;

import static com.imdb.movie.batch.util.FieldUtil.replaceWithNull;
import static java.util.Objects.nonNull;

/**
 * A field mapper for Basic entity.
 *
 * @author gbhat on 17/05/2020.
 */
public class BasicMapper implements FieldSetMapper<Basic> {

    @Override
    public Basic mapFieldSet(FieldSet fieldSet) {
        Basic.BasicBuilder builder = Basic.builder();
        builder.tconst(fieldSet.readString("tconst"));
        builder.titleType(replaceWithNull(fieldSet, "titleType"));
        builder.primaryTitle(replaceWithNull(fieldSet, "primaryTitle"));
        builder.originalTitle(replaceWithNull(fieldSet, "originalTitle"));
        builder.isAdult(fieldSet.readBoolean("isAdult"));
        if (nonNull(replaceWithNull(fieldSet, "startYear"))) {
            builder.startYear(fieldSet.readInt("startYear"));
        }
        if (nonNull(replaceWithNull(fieldSet, "endYear"))) {
            builder.endYear(fieldSet.readInt("endYear"));
        }
        if (nonNull(replaceWithNull(fieldSet, "runtimeMinutes"))) {
            builder.runtimeMinutes(fieldSet.readInt("runtimeMinutes"));
        }
        if (nonNull(replaceWithNull(fieldSet, "genres"))) {
            builder.genres(Set.of(fieldSet.readString("genres").split(",")));
        }
        return builder.build();
    }

}
