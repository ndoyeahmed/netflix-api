package com.netflix.netflixapi.repositories;

import com.netflix.netflixapi.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilRepository extends JpaRepository<Profil, Long> {
    Profil findByLibelle(String libelle);
}
