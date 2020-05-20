package com.imdb.movie.web.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SearchResource} REST controller.
 * <p>
 * @author gbhat on 19/05/2020.
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init.sql")
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clear_init.sql")
public class SearchResourceIT {

    @Autowired
    private MockMvc movieMockMvc;

    @Test
    public void findTypecastInfo_ValidRequest_ShouldReturnResult() throws Exception {

        movieMockMvc.perform(get("/search/typecast").param("firstName", "Pappan Naripatta"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isTypeCasted", is(true)))
                .andExpect(jsonPath("$.genres[*]", hasItem("Action")));
    }

    @Test
    public void findCoincidence_ValidRequest_ShouldReturnResult() throws Exception {

        movieMockMvc.perform(get("/search/coincidence")
                .param("firstName", "Pappan Naripatta")
                .param("secondName", "Mikio Narita"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commonTitles[*]", hasSize(1)))
                .andExpect(jsonPath("$.commonTitles[*]", hasItem("Carmencita")));
    }
}
