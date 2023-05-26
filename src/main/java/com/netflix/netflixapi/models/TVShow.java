package com.netflix.netflixapi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class TVShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTVShow;
    private String name;
    @Column(length = 3000)
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private String first_air_date;
}
