package com.netflix.netflixapi.jwtutils;

import com.netflix.netflixapi.models.Utilisateur;
import com.netflix.netflixapi.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> userSearch = utilisateurRepository.findByLogin(username);

        return userSearch.map(utilisateur -> {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(utilisateur.getProfil().getLibelle()));
        return new User(username, utilisateur.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("L'utilisateur " + username + " n'existe pas!"));
    }
    
}
