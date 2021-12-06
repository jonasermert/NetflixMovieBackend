package com.udemy.movie_backend.netflix.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Movie")
public class MovieEntity {

    @Id
    @GeneratedValue
    private long id;

    private String movieId;
    private String name;
    private String actorName;
    private String description;
    private long date;
}
