package com.netflix.netflixapi.repositories;

import com.netflix.netflixapi.models.TVShow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TVShowRepository extends JpaRepository<TVShow, Long> {
    @Override
    Page<TVShow> findAll(Pageable pageable);
}
