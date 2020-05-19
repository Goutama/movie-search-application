package com.imdb.movie.web.rest;

import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
     * @return the result of the search.
     */
    @ApiOperation("End point to get typecasting info for the input actor/actress")
    @GetMapping("/typecast")
    public TypeCastDTO search(@RequestParam(value = "firstName") final String name) {
        log.info("REST request to get typecast detail for actor/actress {}", name);
        return searchService.search(name);
    }

    /**
     * {@code SEARCH  /coincidence} : search movies/tv-shows acted by input actors/actresses.
     *
     * @param firstName the first actor/actress name.
     * @param secondName the second actor/actress name.
     * @return the list of movie/ tv-shows.
     */
    @ApiOperation("End point to get details of movies/tv-shows acted by input actors/actresses")
    @GetMapping("/coincidence")
    public Set<String> search(@RequestParam(value = "firstName") final String firstName, @RequestParam(value = "secondName") final String secondName) {
        log.info("REST request to get coincidence detail for actors/actresses {} and {}", firstName, secondName);
        return searchService.search(firstName, secondName);
    }
}
