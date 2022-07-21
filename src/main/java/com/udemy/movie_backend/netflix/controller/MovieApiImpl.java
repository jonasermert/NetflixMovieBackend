package com.udemy.movie_backend.netflix.controlleur;

import com.udemy.movie_backend.netflix.model.Movie;
import com.udemy.movie_backend.netflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieApiImpl implements com.udemy.movie_backend.netflix.controlleur.MovieApi {

    private final MovieService service;



    @Override
    public String testStatus() {
        return "Movie Schnittstelle funktioniert gut";
    }

    @Override
    public ResponseEntity<List<Movie>> seeAllMovie() {
        return new ResponseEntity<>(service.seeAllMovie(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Movie> getMovie(String movieId) {
        return new ResponseEntity<>(service.getMovie(movieId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Movie> updateMovie(String movieId, Movie movie) {
        return new ResponseEntity<>(service.updateMovie(movieId, movie), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Movie> createMovie(Movie movie) {
        return new ResponseEntity<>(service.createMovie(movie), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Movie> deleteMovie(String movieId) {
        return new ResponseEntity<>(service.deleteMovie(movieId), HttpStatus.OK);
    }
}
