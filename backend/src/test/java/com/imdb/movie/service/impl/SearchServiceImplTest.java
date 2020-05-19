package com.imdb.movie.service.impl;

import com.imdb.movie.domain.Basic;
import com.imdb.movie.domain.Name;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.exception.NameNotFoundException;
import com.imdb.movie.repository.BasicRepository;
import com.imdb.movie.repository.NameRepository;
import com.imdb.movie.service.SearchService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author gbhat on 19/05/2020.
 */
public class SearchServiceImplTest {

    @Mock
    private NameRepository nameRepository;

    @Mock
    private BasicRepository basicRepository;

    private SearchService searchService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        searchService = new SearchServiceImpl(nameRepository, basicRepository);
    }

    @Test
    public void searchTypecast_ValidInput_ShouldReturnTypeCastTrue() throws NameNotFoundException {
        Name name = Name.builder()
                .primaryName("Pappan Naripatta")
                .build();
        Basic basic = Basic.builder()
                .genres(Set.of("Action"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Optional.of(name));
        Mockito.when(basicRepository.findBasicsByNconsts(any())).thenReturn(Set.of(basic));

        TypeCastDTO typeCastDTO = searchService.search("Pappan Naripatta");

        Assert.assertEquals(true, typeCastDTO.getIsTypeCasted());
        Assert.assertEquals("Action", typeCastDTO.getGenres().get(0));
    }

    @Test
    public void searchTypecast_ValidInput_ShouldReturnTypeCastFalse() throws NameNotFoundException {
        Name name = Name.builder()
                .primaryName("Pappan Naripatta")
                .build();
        Basic basicOne = Basic.builder()
                .tconst("t1")
                .genres(Set.of("Action"))
                .build();
        Basic basicTwo = Basic.builder()
                .tconst("t2")
                .genres(Set.of("Comedy"))
                .build();
        Basic basicThree = Basic.builder()
                .tconst("t3")
                .genres(Set.of("Thriller"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Optional.of(name));
        Mockito.when(basicRepository.findBasicsByNconsts(any())).thenReturn(Set.of(basicOne, basicTwo, basicThree));

        TypeCastDTO typeCastDTO = searchService.search("Pappan Naripatta");

        Assert.assertEquals(false, typeCastDTO.getIsTypeCasted());
        Assert.assertTrue(typeCastDTO.getGenres().isEmpty());
    }

    @Test(expected = NameNotFoundException.class)
    public void findName_InValidInput_ShouldThrowException() throws NameNotFoundException {
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Optional.empty());
        searchService.search("Pappan Naripatta");
    }

    @Test
    public void searchCoincidence_ValidInput_ShouldReturnMovie() throws NameNotFoundException {
        Name nameOne = Name.builder()
                .nconst("n1")
                .primaryName("Pappan Naripatta")
                .build();
        Name nameTwo = Name.builder()
                .nconst("n2")
                .primaryName("Maik Jain")
                .build();
        Basic basic = Basic.builder()
                .tconst("t1")
                .primaryTitle("Carmencita")
                .genres(Set.of("Action"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Optional.of(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Optional.of(nameTwo));
        Mockito.when(basicRepository.findBasicsByNconsts("n1")).thenReturn(Set.of(basic));
        Mockito.when(basicRepository.findBasicsByNconsts("n2")).thenReturn(Set.of(basic));

        Set<String> moviesList = searchService.search("Pappan Naripatta", "Maik Jain");

        Assert.assertThat(moviesList, Matchers.hasItem("Carmencita"));
    }

    @Test
    public void searchCoincidence_ValidInput_ShouldNotReturnMovies() throws NameNotFoundException {
        Name nameOne = Name.builder()
                .nconst("n1")
                .primaryName("Pappan Naripatta")
                .build();
        Name nameTwo = Name.builder()
                .nconst("n2")
                .primaryName("Maik Jain")
                .build();
        Basic basicOne = Basic.builder()
                .tconst("t1")
                .primaryTitle("Carmencita")
                .genres(Set.of("Action"))
                .build();
        Basic basicTwo = Basic.builder()
                .tconst("t2")
                .primaryTitle("TryThisOut")
                .genres(Set.of("Thriller"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Optional.of(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Optional.of(nameTwo));
        Mockito.when(basicRepository.findBasicsByNconsts("n1")).thenReturn(Set.of(basicOne));
        Mockito.when(basicRepository.findBasicsByNconsts("n2")).thenReturn(Set.of(basicTwo));
        Set<String> moviesList = searchService.search("Pappan Naripatta", "Maik Jain");

        Assert.assertTrue(moviesList.isEmpty());
    }
}
