package com.imdb.movie.service.impl;

import com.imdb.movie.domain.Name;
import com.imdb.movie.domain.Title;
import com.imdb.movie.dto.TypeCastDTO;
import com.imdb.movie.exception.NameNotFoundException;
import com.imdb.movie.repository.NameRepository;
import com.imdb.movie.repository.TitleRepository;
import com.imdb.movie.service.NameService;
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

    @Before
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
                .genres(Set.of("Action"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Optional.of(name));
        Mockito.when(titleRepository.findTitlesByNconsts(any())).thenReturn(Set.of(title));

        TypeCastDTO typeCastDTO = nameService.findTypeCastInfo("Pappan Naripatta");

        Assert.assertEquals(true, typeCastDTO.getIsTypeCasted());
        Assert.assertEquals("Action", typeCastDTO.getGenres().get(0));
    }

    @Test
    public void findTypecastInfo_ValidInput_ShouldReturnTypeCastFalse() throws NameNotFoundException {
        Name name = Name.builder()
                .primaryName("Pappan Naripatta")
                .build();
        Title titleOne = Title.builder()
                .tconst("t1")
                .genres(Set.of("Action"))
                .build();
        Title titleTwo = Title.builder()
                .tconst("t2")
                .genres(Set.of("Comedy"))
                .build();
        Title titleThree = Title.builder()
                .tconst("t3")
                .genres(Set.of("Thriller"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Optional.of(name));
        Mockito.when(titleRepository.findTitlesByNconsts(any())).thenReturn(Set.of(titleOne, titleTwo, titleThree));

        TypeCastDTO typeCastDTO = nameService.findTypeCastInfo("Pappan Naripatta");

        Assert.assertEquals(false, typeCastDTO.getIsTypeCasted());
        Assert.assertTrue(typeCastDTO.getGenres().isEmpty());
    }

    @Test(expected = NameNotFoundException.class)
    public void findName_InValidInput_ShouldThrowException() throws NameNotFoundException {
        Mockito.when(nameRepository.findByPrimaryName(any())).thenReturn(Optional.empty());
        nameService.findTypeCastInfo("Pappan Naripatta");
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
                .genres(Set.of("Action"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Optional.of(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Optional.of(nameTwo));
        Mockito.when(titleRepository.findTitlesByNconsts("n1")).thenReturn(Set.of(title));
        Mockito.when(titleRepository.findTitlesByNconsts("n2")).thenReturn(Set.of(title));

        var coincidenceDTO = nameService.findCoincidence("Pappan Naripatta", "Maik Jain");

        Assert.assertThat(coincidenceDTO.getCommonTitles(), Matchers.hasItem("Carmencita"));
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
                .genres(Set.of("Action"))
                .build();
        Title titleTwo = Title.builder()
                .tconst("t2")
                .primaryTitle("TryThisOut")
                .genres(Set.of("Thriller"))
                .build();
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Optional.of(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Optional.of(nameTwo));
        Mockito.when(titleRepository.findTitlesByNconsts("n1")).thenReturn(Set.of(titleOne));
        Mockito.when(titleRepository.findTitlesByNconsts("n2")).thenReturn(Set.of(titleTwo));
        var coincidenceDTO = nameService.findCoincidence("Pappan Naripatta", "Maik Jain");

        Assert.assertTrue(coincidenceDTO.getCommonTitles().isEmpty());
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
        Mockito.when(nameRepository.findByPrimaryName("Pappan Naripatta")).thenReturn(Optional.of(nameOne));
        Mockito.when(nameRepository.findByPrimaryName("Maik Jain")).thenReturn(Optional.of(nameTwo));
        Mockito.when(nameRepository.findDegreesOfSeparation("n1", "n2")).thenReturn((short) 1);
        var linkLevelDTO = nameService.findLinkLevel("Pappan Naripatta", "Maik Jain");

        Assert.assertEquals(1, linkLevelDTO.getLevelOfSeparation().shortValue());
    }
}
