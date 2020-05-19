package com.imdb.movie.batch.util;

import org.springframework.batch.item.file.transform.FieldSet;

/**
 * A util class to help field conversion process.
 *
 * @author gbhat on 17/05/2020.
 */
public final class FieldUtil {
    private FieldUtil(){
    }

    public static <T> String replaceWithNull(FieldSet fieldSet, String name) {
        return fieldSet.readString(name).equals("\\N") ? null : fieldSet.readString(name);
    }
}
