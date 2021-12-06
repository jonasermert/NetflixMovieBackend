package com.udemy.movie_backend.netflix.service;

import com.udemy.movie_backend.netflix.entity.MovieEntity;
import com.udemy.movie_backend.netflix.exception.BadRequestMovieException;
import com.udemy.movie_backend.netflix.exception.NotFoundMovieException;
import com.udemy.movie_backend.netflix.model.Movie;
import com.udemy.movie_backend.netflix.repository.MovieRepository;
import com.udemy.movie_backend.netflix.utils.MovieMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> seeAllMovie() {
        // von DB sollte ich alle MoviesEntity haben
        // alle MovieEntities zum list von Movies umwandeln
        // und diese Liste ausgeben

        List<Movie> movieList = new ArrayList<>();
        for(MovieEntity entity : movieRepository.findAll()){
            movieList.add(MovieMapper.INSTANCE.mapEntityToModel(entity));
        }
        return  movieList;
    }

    public Movie getMovie(@NonNull String movieId) {
        //zuerst movieId sollte nicht null sein
        //movieId in db movieRepository nachsuchen
        //Wenn ich nichts finde, dann sollte eine Exception : NotFoundMovieException mijt 404 response code
        //Wenn ich was finde, dann dann gebe ich diesen Film aus

        MovieEntity entity = movieRepository.findByMovieId(movieId);
        if (entity == null) {
            throw new NotFoundMovieException("Movie existiert nicht");
        }
        Movie movie = MovieMapper.INSTANCE.mapEntityToModel(entity);

        return movie;
    }

    public Movie updateMovie(@NonNull String movieId, Movie movie) {
        //zuerst movieId sollte nicht null sein
        //movieId sollte gleich mit dem Id von Movie sein
        //falls nicht, dann Exception werfen , zb MovieBadRequest


        //movieId in db movieRepository nachsuchen
        //Wenn ich nichts finde, dann sollte eine Exception : NotFoundMovieException mijt 404 response code

        //Wenn ich was finde, dann kann ein Update Infos ausgegeben

        if (!movie.getMovieId().equals(movieId)){
            throw  new BadRequestMovieException("MovieId stimmt nicht");
        }

        MovieEntity entity = movieRepository.findByMovieId(movieId);
        if (entity == null){
            throw new NotFoundMovieException("Movie existiert nicht in db");
        }

        entity.setMovieId(movie.getMovieId());
        entity.setActorName(movie.getActorName());
        entity.setDate(movie.getDate());
        entity.setDescription(movie.getDescription());
        entity.setName(movie.getName());

        movieRepository.save(entity);

        return  MovieMapper.INSTANCE.mapEntityToModel(entity);
    }

    public Movie createMovie(@NonNull Movie movie) {
        //testen, dass movie nicht null ist
        //falls nicht, dann kann man das(MovieEntity) speichern
        // movie ausgeben
        movie.setMovieId(UUID.randomUUID().toString());
        MovieEntity movieEntity = MovieMapper.INSTANCE.mapModelToEntity(movie);
        movieRepository.save(movieEntity);
        return  movie;
    }

    public Movie deleteMovie(@NonNull String movieId) {
        //movieId sollte nicht null sein
        //movieID in db (movieRepository) nachsuchen
        //if movieId notFound, dann exception: NotFoundMovieException
        //sonst, dann löschen
        // das gelöschte Objekt ausgeben

        MovieEntity entity = movieRepository.findByMovieId(movieId);
        if (entity == null){
            throw new NotFoundMovieException("Sorry, Movie existiert nicht für das Löschen");
        }
        movieRepository.delete(entity);
        return MovieMapper.INSTANCE.mapEntityToModel(entity);
    }
}
