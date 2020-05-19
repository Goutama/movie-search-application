package com.imdb.movie.service;

import com.imdb.movie.dto.TypeCastDTO;

import java.util.List;
import java.util.Set;

/**
 * A service class to manage movie search.
 *
 * @author gbhat on 14/05/2020.
 */
public interface SearchService {
    /**
     * Service method to get typecasting info for given input name.
     *
     * @param name the actor/actress name.
     * @return {@link TypeCastDTO} the typecasting info.
     */
    TypeCastDTO search(final String name);

    /**
     * Service method to get movies/tv-shows acted by given input names.
     *
     * @param firstName  the first actor/actress name.
     * @param secondName the second actor/actress name.
     * @return {@link List<TypeCastDTO>} the movie/tv shows list.
     */
    Set<String> search(final String firstName, final String secondName);
}
