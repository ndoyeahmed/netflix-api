package com.netflix.netflixapi.repositories;

import com.netflix.netflixapi.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("SELECT u FROM Utilisateur u WHERE u.login=:login")
    Optional<Utilisateur> findByLogin(@Param("login") String login);

    @Query("SELECT u FROM Utilisateur u WHERE u.profil.libelle=:profil")
    Optional<List<Utilisateur>> findByProfil(@Param("profil") String profil);
}
