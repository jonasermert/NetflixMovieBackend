package com.udemy.movie_backend.netflix.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Movie {

    //name
    //Regisseur name
    //description
    //date
    private String movieId;
    private String name;
    private String actorName;
    private String description;
    private long date;
}
