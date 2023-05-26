package com.netflix.netflixapi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
    private String title;
    @Column(length = 3000)
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private String first_air_date;
}
