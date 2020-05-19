package com.imdb.movie.web.rest;

import com.imdb.movie.MovieSearchApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for the {@link SearchResource} REST controller.
 * <p>
 * @author gbhat on 14/05/2020.
 */
@SpringBootTest(classes = MovieSearchApplication.class)
//@AutoConfigureMockMvc
public class SearchResourceIT {

   /* @Autowired
    private MovieService movieService;

    @Autowired
    private MockMvc movieMockMvc;

    @Autowired
    private ObjectMapper objectMapper;
*/

    @Test
    public void searchMovie_ValidRequest_ShouldReturnResult() throws Exception {

       /* var result = movieMockMvc.perform(get("/movies/_search/inputText=test"))
            .andExpect(status().isOk())
            .andReturn();

        List<MovieDTO> movies = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(!movies.isEmpty());*/
    }
}
