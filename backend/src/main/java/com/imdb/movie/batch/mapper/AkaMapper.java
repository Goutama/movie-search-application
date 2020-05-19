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
        Aka aka = new Aka();
        aka.setTitleId(replaceWithNull(fieldSet, "titleId"));
        aka.setOrdering(fieldSet.readLong("ordering"));
        aka.setTitle(replaceWithNull(fieldSet, "title"));
        aka.setRegion(replaceWithNull(fieldSet, "region"));
        aka.setLanguage(replaceWithNull(fieldSet, "language"));
        if (nonNull(replaceWithNull(fieldSet, "types"))) {
            aka.setTypes(Arrays.asList(fieldSet.readString("types").split(",")));
        }
        if (nonNull(replaceWithNull(fieldSet, "attributes"))) {
            aka.setAttributes(Arrays.asList(fieldSet.readString("attributes").split(",")));
        }
        aka.setOriginalTitle(fieldSet.readBoolean("isOriginalTitle"));
        return aka;
    }
}
