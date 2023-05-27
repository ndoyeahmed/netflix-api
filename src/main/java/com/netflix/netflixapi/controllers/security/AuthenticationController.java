package com.netflix.netflixapi.controllers.security;

import com.netflix.netflixapi.controllers.exceptions.BadRequestException;
import com.netflix.netflixapi.jwtutils.JwtRequestModel;
import com.netflix.netflixapi.jwtutils.JwtResponseModel;
import com.netflix.netflixapi.jwtutils.JwtUserDetailsService;
import com.netflix.netflixapi.jwtutils.TokenManager;
import com.netflix.netflixapi.models.Utilisateur;
import com.netflix.netflixapi.services.Utilitaire;
import com.netflix.netflixapi.services.security.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private Utilitaire utilitaire;

    @PostMapping("/api/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel request) throws Exception {
        try {
            authenticationManager.authenticate(
                new
                UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword())
            );
        } catch (DisabledException e) {
            throw new BadRequestException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new BadRequestException("INVALID_CREDENTIALS");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }

    @GetMapping("/api/connected-user")
    public MappingJacksonValue connectedUser() {
        try {
            Utilisateur utilisateur = utilisateurService.connectedUser();
            return utilitaire.getFilter(utilisateur, "passwordFilter", "password");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
