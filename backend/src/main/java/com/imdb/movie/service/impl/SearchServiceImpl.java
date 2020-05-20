package com.imdb.movie.service.impl;

import com.imdb.movie.domain.Name;
import com.imdb.movie.domain.Title;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.exception.NameNotFoundException;
import com.imdb.movie.repository.NameRepository;
import com.imdb.movie.repository.TitleRepository;
import com.imdb.movie.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A service class to manage names.
 *
 * @author gbhat on 14/05/2020.
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final NameRepository nameRepository;
    private final TitleRepository titleRepository;

    /**
     * The default constructor.
     *
     * @param nameRepository  the name repository.
     * @param titleRepository the title repository.
     */
    public SearchServiceImpl(NameRepository nameRepository, TitleRepository titleRepository) {
        this.nameRepository = nameRepository;
        this.titleRepository = titleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public TypeCastDTO search(final String name) throws NameNotFoundException {

        var isTypeCasted = false;

        Set<Title> titles = retrieveTitlesForName(name);

        var genresGroupedByCount =
                titles.stream()
                        .map(Title::getGenres)
                        .flatMap(Collection::stream)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var typeCastedGenres =
                genresGroupedByCount.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() >= (double) titles.size() / 2)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());


        if (typeCastedGenres.size() >= 1) {
            isTypeCasted = true;
        }

        var builder =
                TypeCastDTO.builder()
                        .isTypeCasted(isTypeCasted)
                        .genres(typeCastedGenres);
        return builder.build();
    }


    private Set<Title> retrieveTitlesForName(String name) throws NameNotFoundException {
        Name nameFromDb = nameRepository.findByPrimaryName(name)
                .orElseThrow(() ->
                        new NameNotFoundException("The actor/actress with name " + name + " not found")
                );
        return titleRepository.findTitlesByNconsts(nameFromDb.getNconst());
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Set<String> search(final String firstName, final String secondName) throws NameNotFoundException {
        var titlesOfFirstName = retrieveTitlesForName(firstName);
        var titlesOfSecondName = retrieveTitlesForName(secondName);
        Set<Title> result = new HashSet<>(titlesOfFirstName);
        result.retainAll(titlesOfSecondName);
        return result.stream().map(Title::getPrimaryTitle).collect(Collectors.toSet());
    }
}
