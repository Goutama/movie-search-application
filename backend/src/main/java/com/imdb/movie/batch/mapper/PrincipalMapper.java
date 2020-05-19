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
        Principal principal = new Principal();
        principal.setTconst(fieldSet.readString("tconst"));
        principal.setOrdering(fieldSet.readLong("ordering"));
        principal.setNconst(fieldSet.readString("nconst"));
        principal.setCategory(replaceWithNull(fieldSet, "category"));
        principal.setJob(replaceWithNull(fieldSet, "job"));
        principal.setCharacters(replaceWithNull(fieldSet, "characters"));
        return principal;
    }
}
