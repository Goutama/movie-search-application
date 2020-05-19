package com.imdb.movie.batch.mapper;

import com.imdb.movie.domain.Name;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.Arrays;

import static com.imdb.movie.batch.util.FieldUtil.replaceWithNull;
import static java.util.Objects.nonNull;

/**
 * A field mapper for Name entity.
 *
 * @author gbhat on 17/05/2020.
 */
public class NameMapper implements FieldSetMapper<Name> {

    @Override
    public Name mapFieldSet(FieldSet fieldSet) {
        Name.NameBuilder builder = Name.builder();
        builder.nconst(fieldSet.readString("nconst"));
        builder.primaryName(replaceWithNull(fieldSet, "primaryName"));
        if (nonNull(replaceWithNull(fieldSet, "birthYear"))) {
            builder.birthYear(fieldSet.readInt("birthYear"));
        }
        if (nonNull(replaceWithNull(fieldSet, "deathYear"))) {
            builder.deathYear(fieldSet.readInt("deathYear"));
        }
        if (nonNull(replaceWithNull(fieldSet, "primaryProfessions"))) {
            builder.primaryProfessions(Arrays.asList(fieldSet.readString("primaryProfessions").split(",")));
        }
        if (nonNull(replaceWithNull(fieldSet, "knownForTitles"))) {
            builder.knownForTitles(Arrays.asList(fieldSet.readString("knownForTitles").split(",")));
        }
        return builder.build();
    }
}
