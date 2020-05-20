package com.imdb.movie.service;

import com.imdb.movie.dto.CoincidenceDTO;
import com.imdb.movie.dto.LinkLevelDTO;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.exception.NameNotFoundException;

import java.util.List;

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
     * @throws NameNotFoundException if the input name is not found.
     */
    TypeCastDTO findTypeCastInfo(final String name) throws NameNotFoundException;

    /**
     * Service method to get movies and tv shows acted by given input names.
     *
     * @param sourceName  the source actor/actress name.
     * @param targetName the target actor/actress name.
     * @return {@link List<TypeCastDTO>} the movie and tv shows names.
     * @throws NameNotFoundException if the input names are not found.
     */
    CoincidenceDTO findCoincidence(final String sourceName, final String targetName) throws NameNotFoundException;

    /**
     * Service method to get degrees of separation between provided names.
     *
     * @param sourceName the source actor/actress name.
     * @param targetName the target actor/actress name.
     * @return  the degrees of separation.
     * @throws NameNotFoundException if the input names are not found.
     */
    LinkLevelDTO findLinkLevel(final String sourceName, final String targetName) throws NameNotFoundException;
}
