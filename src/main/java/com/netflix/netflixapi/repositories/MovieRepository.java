package com.netflix.netflixapi.repositories;

import com.netflix.netflixapi.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Override
    Page<Movie> findAll(Pageable pageable);
}
