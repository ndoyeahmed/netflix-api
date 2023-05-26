package com.netflix.netflixapi.controllers;

import com.netflix.netflixapi.controllers.exceptions.InternalServerErrorException;
import com.netflix.netflixapi.models.Movie;
import com.netflix.netflixapi.models.TVShow;
import com.netflix.netflixapi.services.movies.MovieService;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@Log
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movies")
    public ResponseEntity<?> getAllTrendingMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Movie> moviessPage = movieService.getAllTrendingMovies(paging);
            Map<String, Object> response = new HashMap<>();
            response.put("movies", moviessPage.getContent());
            response.put("currentPage", moviessPage.getNumber());
            response.put("totalItems", moviessPage.getTotalElements());
            response.put("totalPages", moviessPage.getTotalPages());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            log.info(e.getLocalizedMessage());
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    @GetMapping("tvshows")
    public ResponseEntity<?> getAllTVShow(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<TVShow> tvshowsPage = movieService.getAllTopRatedTVShow(paging);
            Map<String, Object> response = new HashMap<>();
            response.put("tvshows", tvshowsPage.getContent());
            response.put("currentPage", tvshowsPage.getNumber());
            response.put("totalItems", tvshowsPage.getTotalElements());
            response.put("totalPages", tvshowsPage.getTotalPages());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            log.info(e.getLocalizedMessage());
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}
