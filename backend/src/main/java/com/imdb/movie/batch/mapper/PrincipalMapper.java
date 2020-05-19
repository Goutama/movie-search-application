package com.imdb.movie.batch.mapper;

import com.imdb.movie.domain.Principal;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import static com.imdb.movie.batch.util.FieldUtil.replaceWithNull;

/**
 * A field mapper for Principal.
 *
 * @author gbhat on 17/05/2020.
 */
public class PrincipalMapper implements FieldSetMapper<Principal> {

    @Override
    public Principal mapFieldSet(FieldSet fieldSet) {
        Principal.PrincipalBuilder builder = Principal.builder();
        builder.tconst(fieldSet.readString("tconst"));
        builder.ordering(fieldSet.readLong("ordering"));
        builder.nconst(fieldSet.readString("nconst"));
        builder.category(replaceWithNull(fieldSet, "category"));
        builder.job(replaceWithNull(fieldSet, "job"));
        builder.characters(replaceWithNull(fieldSet, "characters"));
        return builder.build();
    }
}
