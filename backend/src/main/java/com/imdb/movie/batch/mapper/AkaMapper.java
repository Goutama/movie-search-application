package com.imdb.movie.batch.mapper;

import com.imdb.movie.domain.Aka;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.Arrays;

import static com.imdb.movie.batch.util.FieldUtil.replaceWithNull;
import static java.util.Objects.nonNull;

/**
 * A field mapper for Aka.
 *
 * @author gbhat on 17/05/2020.
 */
public class AkaMapper implements FieldSetMapper<Aka> {

    @Override
    public Aka mapFieldSet(FieldSet fieldSet) {
        Aka.AkaBuilder builder = Aka.builder();
        builder.titleId(replaceWithNull(fieldSet, "titleId"));
        builder.ordering(fieldSet.readLong("ordering"));
        builder.title(replaceWithNull(fieldSet, "title"));
        builder.region(replaceWithNull(fieldSet, "region"));
        builder.language(replaceWithNull(fieldSet, "language"));
        if (nonNull(replaceWithNull(fieldSet, "types"))) {
            builder.types(Arrays.asList(fieldSet.readString("types").split(",")));
        }
        if (nonNull(replaceWithNull(fieldSet, "attributes"))) {
            builder.attributes(Arrays.asList(fieldSet.readString("attributes").split(",")));
        }
        builder.isOriginalTitle(fieldSet.readBoolean("isOriginalTitle"));
        return builder.build();
    }
}
