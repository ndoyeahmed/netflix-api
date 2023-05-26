package com.netflix.netflixapi.services.utils.dto;

import com.netflix.netflixapi.models.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MovieResponse {
    private int page;
    private List<Movie> results;
}
