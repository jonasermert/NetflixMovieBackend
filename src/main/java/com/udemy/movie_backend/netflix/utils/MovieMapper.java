package com.udemy.movie_backend.netflix.utils;

import com.udemy.movie_backend.netflix.entity.MovieEntity;
import com.udemy.movie_backend.netflix.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    //von entity zu Model mappen
    Movie mapEntityToModel(MovieEntity entity);

    //von Model zum Entity mappen
    MovieEntity mapModelToEntity(Movie movie);
}
