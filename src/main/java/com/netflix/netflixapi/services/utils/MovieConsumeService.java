package com.netflix.netflixapi.services.utils;

import com.netflix.netflixapi.services.utils.dto.MovieResponse;
import com.netflix.netflixapi.services.utils.dto.TVShowResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieConsumeService {
    @Value("${bearer_token}")
    private String bearToken;
    private final String baseUri = "https://api.themoviedb.org/3/";
    public TVShowResponse getTopRatedTVShow() {
        String uri = baseUri + "tv/top_rated?language=fr-FR&page=1";
        RestTemplate restTemplate = new RestTemplate();
        // Create HttpHeaders and set the desired headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearToken);

        // Create an HttpEntity with the headers
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        // Make the request with the specified headers
        ResponseEntity<TVShowResponse> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, TVShowResponse.class);

        // Process the response as needed
        return response.getBody();
    }

    public MovieResponse getTrendingMovie() {
        String uri = baseUri + "trending/movie/day?language=fr-FR";
        RestTemplate restTemplate = new RestTemplate();
        // Create HttpHeaders and set the desired headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearToken);

        // Create an HttpEntity with the headers
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        // Make the request with the specified headers
        ResponseEntity<MovieResponse> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MovieResponse.class);

        // Process the response as needed
        return response.getBody();
    }
}
