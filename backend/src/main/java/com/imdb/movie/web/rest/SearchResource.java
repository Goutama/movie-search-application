package com.imdb.movie.web.rest;

import com.imdb.movie.dto.CoincidenceDTO;
import com.imdb.movie.dto.LinkLevelDTO;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.exception.NameNotFoundException;
import com.imdb.movie.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Search search.
 *
 * @author gbhat on 18/05/2020.
 */
@Api(value = "Resource to handle search search")
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchResource {

    private final SearchService searchService;

    /**
     * The default constructor.
     *
     * @param searchService the search service.
     */
    public SearchResource(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * {@code SEARCH  /typecast} : search typecasting info for the input actor/actress.
     *
     * @param name the actor/actress name.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the typecast info in body.
     * @throws NameNotFoundException if the input name is not found.
     */
    @ApiOperation("End point to get typecasting info for the input actor/actress")
    @GetMapping("/typecast")
    public TypeCastDTO findTypeCastInfo(@RequestParam(value = "name") final String name) throws NameNotFoundException {
        log.info("REST request to get typecast detail for actor/actress {}", name);
        return searchService.findTypeCastInfo(name);
    }

    /**
     * {@code SEARCH  /coincidence} : search common movies and tv shows acted by input actors/actresses.
     *
     * @param sourceName  the source actor/actress name.
     * @param targetName the target actor/actress name.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the movies and tv shows info in body.
     * @throws NameNotFoundException if the input name is not found.
     */
    @ApiOperation("End point to get details of movies and tv shows acted by input actors/actresses")
    @GetMapping("/coincidence")
    public CoincidenceDTO findCoincidence(@RequestParam(value = "sourceName") final String sourceName, @RequestParam(value = "targetName") final String targetName) throws NameNotFoundException {
        log.info("REST request to get coincidence detail for actors/actresses {} and {}", sourceName, targetName);
        return searchService.findCoincidence(sourceName, targetName);
    }

    /**
     * {@code SEARCH  /degrees-of-separation} : search degrees of separation between input actors/actresses.
     *
     * @param sourceName  the source actor/actress name.
     * @param targetName the target actor/actress name.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the degrees of separation in body.
     * @throws NameNotFoundException if the input name is not found.
     */
    @ApiOperation("End point to get degrees of separation between input actors/actresses")
    @GetMapping("/degrees-of-separation")
    public LinkLevelDTO findLinkLevel(@RequestParam(value = "sourceName") final String sourceName, @RequestParam(value = "targetName", defaultValue = "Kelvin Becon") final String targetName) throws NameNotFoundException {
        log.info("REST request to get degrees of separation between actors/actresses {} and {}", sourceName, targetName);
        return searchService.findLinkLevel(sourceName, targetName);
    }
}
