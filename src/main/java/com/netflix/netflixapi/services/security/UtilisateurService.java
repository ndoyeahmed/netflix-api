package com.netflix.netflixapi.services.security;

import com.netflix.netflixapi.models.Profil;
import com.netflix.netflixapi.models.Utilisateur;
import com.netflix.netflixapi.repositories.ProfilRepository;
import com.netflix.netflixapi.repositories.UtilisateurRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Log
public class UtilisateurService {
  @Autowired
  private UtilisateurRepository utilisateurRepository;
  @Autowired
  private ProfilRepository profilRepository;

  public Utilisateur connectedUser() {
    try {
      SecurityContext context = SecurityContextHolder.getContext();
      Authentication authentication = context.getAuthentication();
      String login = "";
      if (authentication.getPrincipal() instanceof UserDetails) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        login = user.getUsername();
      }
      if (authentication.getPrincipal() instanceof String)
        login = (String) authentication.getPrincipal();
      return utilisateurRepository
          .findByLogin(login)
          .orElse(null);
    } catch (Exception e) {
      log.severe(e.getLocalizedMessage());
      throw e;
    }
  }

  public List<Profil> getAllProfil() {
    return profilRepository.findAll();
  }

  public boolean hasProfils(String[] profils) {
    Utilisateur conectedUser = connectedUser();
    for (String role : profils) {
      if (role.equals(conectedUser.getProfil().getLibelle())) {
        return true;
      }
    }
    return false;
  }

  public boolean addAllProfils(List<Profil> profils) {
    try {
      profilRepository.saveAll(profils);
      return true;
    } catch (Exception e) {
      throw e;
    }
  }

  public Profil getProfilByLibelle(String libelle) {
    try {
      return profilRepository.findByLibelle(libelle);
    } catch (Exception e) {
      return null;
    }
  }

  public Utilisateur addUser(Utilisateur user) {
    try {
      utilisateurRepository.save(user);
      return user;
    } catch (Exception e) {
      throw e;
    }
  }

  public List<Utilisateur> getUsersByProfil(String profil) {
    return utilisateurRepository.findByProfil(profil).orElse(new ArrayList<>());
  }

}
