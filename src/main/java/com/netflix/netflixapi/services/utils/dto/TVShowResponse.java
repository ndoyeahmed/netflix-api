package com.netflix.netflixapi.services.utils.dto;

import com.netflix.netflixapi.models.TVShow;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TVShowResponse {
    private int page;
    private List<TVShow> results;
}
