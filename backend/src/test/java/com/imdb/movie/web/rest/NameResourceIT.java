package com.imdb.movie.web.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NameResource} REST controller.
 * <p>
 * @author gbhat on 19/05/2020.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data/init.sql")
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:data/clear_init.sql")
public class NameResourceIT {

    @Autowired
    private MockMvc movieMockMvc;

    @Test
    public void findTypecastInfo_ValidRequest_ShouldReturnResult() throws Exception {

        movieMockMvc.perform(get("/names/typecast").param("name", "Pappan Naripatta"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isTypeCasted", is(true)))
                .andExpect(jsonPath("$.genres[*]", hasItem("Action")));
    }

    @Test
    public void findCoincidence_ValidRequest_ShouldReturnResult() throws Exception {

        movieMockMvc.perform(get("/names/coincidence")
                .param("sourceName", "Pappan Naripatta")
                .param("targetName", "Mikio Narita"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commonTitles[*]", hasSize(1)))
                .andExpect(jsonPath("$.commonTitles[*]", hasItem("Carmencita")));
    }

    // Not possible to test in H2 database (since recursive future used in the query).
    // ToDo - Need to setup postgres test DB for testing.
    @Test
    public void findLinkLevel_ValidRequest_ShouldReturnResult() throws Exception {

        /*movieMockMvc.perform(get("/names/degrees-of-separation")
                .param("sourceName", "Pappan Naripatta")
                .param("targetName", "Mikio Narita"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.linkLevel", is(1)));*/
    }
}
