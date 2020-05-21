package com.imdb.movie.service.impl;

import com.imdb.movie.domain.Name;
import com.imdb.movie.domain.Title;
import com.imdb.movie.dto.CoincidenceDTO;
import com.imdb.movie.dto.LinkLevelDTO;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.exception.NameNotFoundException;
import com.imdb.movie.repository.NameRepository;
import com.imdb.movie.repository.TitleRepository;
import com.imdb.movie.service.NameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * A service class to manage name related operations.
 *
 * @author gbhat on 14/05/2020.
 */
@Service
@Slf4j
public class NameServiceImpl implements NameService {

    private final NameRepository nameRepository;
    private final TitleRepository titleRepository;

    /**
     * The default constructor.
     *
     * @param nameRepository  the name repository.
     * @param titleRepository the title repository.
     */
    public NameServiceImpl(NameRepository nameRepository, TitleRepository titleRepository) {
        this.nameRepository = nameRepository;
        this.titleRepository = titleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public TypeCastDTO findTypeCastInfo(final String name) throws NameNotFoundException {

        var isTypeCasted = false;

        var titles = retrieveTitlesForName(name);

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
                        .name(name)
                        .isTypeCasted(isTypeCasted)
                        .genres(typeCastedGenres);
        return builder.build();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public CoincidenceDTO findCoincidence(final String sourceName, final String targetName) throws NameNotFoundException {

        var titlesOfSourceName = retrieveTitlesForName(sourceName);
        var titlesOfTargetName = retrieveTitlesForName(targetName);

        Set<Title> result = new HashSet<>(titlesOfSourceName);
        result.retainAll(titlesOfTargetName);

        var builder =
                CoincidenceDTO.builder()
                        .sourceName(sourceName)
                        .targetName(targetName)
                        .commonTitles(result.stream().map(Title::getPrimaryTitle).collect(Collectors.toSet()));
        return builder.build();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public LinkLevelDTO findLinkLevel(final String sourceName, final String targetName) throws NameNotFoundException {

        var sourceNameObj = retrieveName(sourceName);
        var targetNameObj = retrieveName(targetName);

        var distance = nameRepository.findDegreesOfSeparation(sourceNameObj.getNconst(), targetNameObj.getNconst());

        var builder =
                LinkLevelDTO.builder()
                        .sourceName(sourceName)
                        .targetName(targetName)
                        .levelOfSeparation(distance == null ? -1 : distance);
        return builder.build();
    }

    private Set<Title> retrieveTitlesForName(String name) throws NameNotFoundException {
        Name nameFromDb = retrieveName(name);
        return titleRepository.findTitlesByNconsts(nameFromDb.getNconst());
    }

    private Name retrieveName(String name) throws NameNotFoundException {
        List<Name> names = nameRepository.findByPrimaryName(name);
        if (isEmpty(names)) {
            throw new NameNotFoundException("The actor/actress with name " + name + " not found");
        }
        return names.get(0);
    }
}
