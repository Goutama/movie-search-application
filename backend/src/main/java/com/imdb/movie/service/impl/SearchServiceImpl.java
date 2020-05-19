package com.imdb.movie.service.impl;

import com.imdb.movie.domain.Basic;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.repository.BasicRepository;
import com.imdb.movie.repository.NameRepository;
import com.imdb.movie.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.imdb.movie.dto.TypeCastDTO.TypeCastDTOBuilder;
import static com.imdb.movie.dto.TypeCastDTO.builder;

/**
 * A service class to manage names.
 *
 * @author gbhat on 14/05/2020.
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final NameRepository nameRepository;
    private final BasicRepository basicRepository;

    /**
     * The default constructor.
     *
     * @param nameRepository  the name repository.
     * @param basicRepository the basic repository.
     */
    public SearchServiceImpl(NameRepository nameRepository, BasicRepository basicRepository) {
        this.nameRepository = nameRepository;
        this.basicRepository = basicRepository;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public TypeCastDTO search(final String name) {

        var isTypeCasted = false;

        Set<Basic> titles = retrieveTitlesForName(name);

        var genresGroupedByCount =
                titles.stream()
                        .map(Basic::getGenres)
                        .flatMap(Collection::stream)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var typeCastedGenres =
                genresGroupedByCount.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() >= titles.size() / 2)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());


        if (typeCastedGenres.size() >= 1) {
            isTypeCasted = true;
        }

        TypeCastDTOBuilder builder =
                builder()
                        .isTypeCasted(isTypeCasted)
                        .genres(typeCastedGenres);
        return builder.build();
    }


    private Set<Basic> retrieveTitlesForName(String name) {
        var actor = nameRepository.findByPrimaryName(name);
        return basicRepository.findBasicsByNconsts(actor.getNconst());
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public Set<String> search(final String firstName, final String secondName) {
        var titlesOfFirstName = retrieveTitlesForName(firstName);
        var titlesOfSecondName = retrieveTitlesForName(secondName);
        Set<Basic> result = new HashSet<>(titlesOfFirstName);
        result.retainAll(titlesOfSecondName);
        return result.stream().map(Basic::getPrimaryTitle).collect(Collectors.toSet());
    }
}
