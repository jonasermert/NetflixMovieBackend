package com.udemy.movie_backend.netflix.repository;

import com.udemy.movie_backend.netflix.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    //ein Movie in db speichern : save


    //ein Movie aufrufen
    MovieEntity findByMovieId(String movieId);




    //ein Movie löschen : delete
    //ein Movie modifizieren:  save
}
