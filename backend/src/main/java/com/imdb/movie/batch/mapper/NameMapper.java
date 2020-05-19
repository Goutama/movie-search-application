package com.imdb.movie.batch.mapper;

import com.imdb.movie.domain.Name;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.Arrays;

import static com.imdb.movie.batch.util.FieldUtil.replaceWithNull;
import static java.util.Objects.nonNull;

/**
 * A field mapper for Name.
 *
 * @author gbhat on 17/05/2020.
 */
public class NameMapper implements FieldSetMapper<Name> {

    @Override
    public Name mapFieldSet(FieldSet fieldSet) {
        Name name = new Name();
        name.setNconst(fieldSet.readString("nconst"));
        name.setPrimaryName(replaceWithNull(fieldSet, "primaryName"));
        name.setBirthYear(replaceWithNull(fieldSet, "birthYear"));
        name.setDeathYear(replaceWithNull(fieldSet, "deathYear"));
        if (nonNull(replaceWithNull(fieldSet, "primaryProfessions"))) {
            name.setPrimaryProfessions(Arrays.asList(fieldSet.readString("primaryProfessions").split(",")));
        }
        if (nonNull(replaceWithNull(fieldSet, "knownForTitles"))) {
            name.setKnownForTitles(Arrays.asList(fieldSet.readString("knownForTitles").split(",")));
        }
        return name;
    }
}
