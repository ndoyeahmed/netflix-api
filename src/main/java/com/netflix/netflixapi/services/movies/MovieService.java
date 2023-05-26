package com.netflix.netflixapi.services.movies;

import com.netflix.netflixapi.models.Movie;
import com.netflix.netflixapi.models.TVShow;
import com.netflix.netflixapi.repositories.MovieRepository;
import com.netflix.netflixapi.repositories.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private TVShowRepository tvShowRepository;

    public MovieService(MovieRepository movieRepository, TVShowRepository tvShowRepository) {
        this.movieRepository = movieRepository;
        this.tvShowRepository = tvShowRepository;
    }

    public Movie addMovie(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    public List<Movie> addAllMovie(List<Movie> movies) {
        movieRepository.saveAll(movies);
        return movies;
    }

    public Page<Movie> getAllTrendingMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public boolean removeAllMovies() {
        movieRepository.deleteAll();
        return true;
    }

    public TVShow addTVShow(TVShow tvShow) {
        tvShowRepository.save(tvShow);
        return tvShow;
    }

    public List<TVShow> addAllTVShow(List<TVShow> tvShows) {
        tvShowRepository.saveAll(tvShows);
        return tvShows;
    }

    public Page<TVShow> getAllTopRatedTVShow(Pageable pageable) {
        return tvShowRepository.findAll(pageable);
    }

    public boolean removeAllTVShow() {
        tvShowRepository.deleteAll();
        return true;
    }
}
