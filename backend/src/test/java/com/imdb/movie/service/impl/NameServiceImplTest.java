package com.imdb.movie.service.impl;

import com.google.common.collect.Sets;
import com.imdb.movie.domain.Name;
import com.imdb.movie.domain.Title;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.exception.NameNotFoundException;
import com.imdb.movie.repository.NameRepository;
import com.imdb.movie.repository.TitleRepository;
import com.imdb.movie.service.NameService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

/**
 * A unit test class for search service {@link NameServiceImpl} implementation.
 *
 * @author gbhat on 19/05/2020.
 */
public class NameServiceImplTest {

    @Mock
    private NameRepository nameRepository;

    @Mock
    private TitleRepository titleRepository;

    private NameService nameService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        nameService = new NameServiceImpl(nameRepository, titleRepository);
    }

    @Test
    public void findTypecastInfo_ValidInput_ShouldReturnTypeCastTrue() throws NameNotFoundException {
        Name name = Name.builder()
                .primaryName("Pappan Naripatta")
                .build();
        Title title = Title.builder()
                .genres(Sets.newHashSet("Action"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Collections.singletonList(name));
        Mockito.when(titleRepository.findTitlesByNconsts(any())).thenReturn(Sets.newHashSet(title));

        TypeCastDTO typeCastDTO = nameService.findTypeCastInfo("Pappan Naripatta");

        assertEquals(true, typeCastDTO.getIsTypeCasted());
        assertEquals("Action", typeCastDTO.getGenres().get(0));
    }

    @Test
    public void findTypecastInfo_ValidInput_ShouldReturnTypeCastFalse() throws NameNotFoundException {
        Name name = Name.builder()
                .primaryName("Pappan Naripatta")
                .build();
        Title titleOne = Title.builder()
                .tconst("t1")
                .genres(Sets.newHashSet("Action"))
                .build();
        Title titleTwo = Title.builder()
                .tconst("t2")
                .genres(Sets.newHashSet("Comedy"))
                .build();
        Title titleThree = Title.builder()
                .tconst("t3")
                .genres(Sets.newHashSet("Thriller"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Collections.singletonList(name));
        Mockito.when(titleRepository.findTitlesByNconsts(any())).thenReturn(Sets.newHashSet(titleOne, titleTwo, titleThree));

        TypeCastDTO typeCastDTO = nameService.findTypeCastInfo("Pappan Naripatta");

        assertEquals(false, typeCastDTO.getIsTypeCasted());
        assertTrue(typeCastDTO.getGenres().isEmpty());
    }

    @Test
    public void findName_InValidInput_ShouldThrowException() throws NameNotFoundException {
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(null);
        Assertions.assertThrows(NameNotFoundException.class, () -> {
            nameService.findTypeCastInfo("Pappan Naripatta");
        });
    }

    @Test
    public void findCoincidence_ValidInput_ShouldReturnMovie() throws NameNotFoundException {
        Name nameOne = Name.builder()
                .nconst("n1")
                .primaryName("Pappan Naripatta")
                .build();
        Name nameTwo = Name.builder()
                .nconst("n2")
                .primaryName("Maik Jain")
                .build();
        Title title = Title.builder()
                .tconst("t1")
                .primaryTitle("Carmencita")
                .genres(Sets.newHashSet("Action"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Collections.singletonList(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Collections.singletonList(nameTwo));
        Mockito.when(titleRepository.findTitlesByNconsts("n1")).thenReturn(Sets.newHashSet(title));
        Mockito.when(titleRepository.findTitlesByNconsts("n2")).thenReturn(Sets.newHashSet(title));

        var coincidenceDTO = nameService.findCoincidence("Pappan Naripatta", "Maik Jain");

        assertThat(coincidenceDTO.getCommonTitles(), Matchers.hasItem("Carmencita"));
    }

    @Test
    public void findCoincidence_ValidInput_ShouldNotReturnMovies() throws NameNotFoundException {
        Name nameOne = Name.builder()
                .nconst("n1")
                .primaryName("Pappan Naripatta")
                .build();
        Name nameTwo = Name.builder()
                .nconst("n2")
                .primaryName("Maik Jain")
                .build();
        Title titleOne = Title.builder()
                .tconst("t1")
                .primaryTitle("Carmencita")
                .genres(Sets.newHashSet("Action"))
                .build();
        Title titleTwo = Title.builder()
                .tconst("t2")
                .primaryTitle("TryThisOut")
                .genres(Sets.newHashSet("Thriller"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Collections.singletonList(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Collections.singletonList(nameTwo));
        Mockito.when(titleRepository.findTitlesByNconsts("n1")).thenReturn(Sets.newHashSet(titleOne));
        Mockito.when(titleRepository.findTitlesByNconsts("n2")).thenReturn(Sets.newHashSet(titleTwo));
        var coincidenceDTO = nameService.findCoincidence("Pappan Naripatta", "Maik Jain");

        assertTrue(coincidenceDTO.getCommonTitles().isEmpty());
    }

    @Test
    public void findLinkLevel_ValidInput_ShouldReturnLinkLevel() throws NameNotFoundException {
        Name nameOne = Name.builder()
                .nconst("n1")
                .primaryName("Pappan Naripatta")
                .build();
        Name nameTwo = Name.builder()
                .nconst("n2")
                .primaryName("Maik Jain")
                .build();
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Collections.singletonList(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Collections.singletonList(nameTwo));
        Mockito.when(nameRepository.findDegreesOfSeparation("n1", "n2")).thenReturn((short) 1);
        var linkLevelDTO = nameService.findLinkLevel("Pappan Naripatta", "Maik Jain");

        assertEquals(1, linkLevelDTO.getLevelOfSeparation().shortValue());
    }
}
