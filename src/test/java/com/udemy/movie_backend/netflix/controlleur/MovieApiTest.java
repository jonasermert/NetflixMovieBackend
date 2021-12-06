package com.udemy.movie_backend.netflix.controlleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.movie_backend.netflix.model.Movie;
import com.udemy.movie_backend.netflix.service.MovieService;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(MovieApiImpl.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieApiTest {

    private final String GET_ALL_MOVIES  = "/movie_backend/movies";
    private final String TEST_STATUS = "/movie_backend/status";
    private final String GETMOVIE = "/movie_backend/movies/get/";
    private final String UPDATEMOVIE = "/movie_backend/movies/put/";
    private final String CREATEMOVIE = "/movie_backend/movie";
    private final String DELETEMOVIE = "/movie_backend/movies/delete/";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MovieService service;


    @Test
    public void testStatus() throws Exception {
        //wir wollen zuerst eine url (/movie_backend/status) eingeben
        // Http methode absetzen Get
        // Request schicken
        //am Ende ein Server response code : 200
        //ich sollte genau testen dass es 200 gibt
        MockHttpServletResponse response = mockMvc.perform(get(TEST_STATUS)
        .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.OK.value()));

    }

    @Test
    public void testGetAllMovie() throws Exception {
        //  eine url ("/movie_backend/movies") eingeben
        // http methode : get
        // testen dass 200 rauskommt
        MockHttpServletResponse response = mockMvc.perform(get(GET_ALL_MOVIES)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

    @Test
    public void testGetMovie() throws Exception {
        // eine Url ("/movie_backend/movies/get/") + movieId eingeben. Zum Beispiel : movieId = test
        //Http methode : get
        // testen dass 200 rauskommt.

        MockHttpServletResponse response = mockMvc.perform(get(GETMOVIE + "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

    @Test
    public void testGetMovieWithWrongApi() throws Exception {
        // eine Url ("/movie_backend/movies/get") + movieId eingeben. Zum Beispiel : movieId = test
        //Http methode : get
        // testen dass 404 rauskommt.
        MockHttpServletResponse response = mockMvc.perform(get("/movie_backend/movies/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void testUpdateMovie() throws Exception {
        // eine Url ("/movie_backend/movies/put/") + movieId eingeben. Zum Beispiel : movieId = test
        //Http methode : put
        // testen dass 200 rauskommt.
        Movie movie = new Movie();
        movie.setMovieId("test");
        movie.setDescription("description");
        movie.setDate(123);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(movie);
        MockHttpServletResponse response = mockMvc.perform(put( UPDATEMOVIE + "test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                )
                .andReturn().getResponse();
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

    @Test
    public void testCreateMovie() throws Exception {
        // eine Url ("/movie_backend/movie") und ein Objekt Movie eingeben. Zum Beispiel: Movie : ist ein leeres Objekt
        //Http methode : post
        // testen dass 201 rauskommt.
        MockHttpServletResponse response = mockMvc.perform(post(CREATEMOVIE)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn().getResponse();
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));
    }



    @Test
    public void testDeleteMovie() throws Exception {
        // eine Url ("/movie_backend/movies/delete/") + movieId eingeben. Zum Beispiel : movieId = test
        //Http methode : Delete
        // testen dass 200 rauskommt.

        MockHttpServletResponse response = mockMvc.perform(delete( DELETEMOVIE + "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }
}
