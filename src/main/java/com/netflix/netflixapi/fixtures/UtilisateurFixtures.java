package com.netflix.netflixapi.fixtures;

import com.netflix.netflixapi.models.Profil;
import com.netflix.netflixapi.models.Utilisateur;
import com.netflix.netflixapi.services.security.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UtilisateurFixtures {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
	private PasswordEncoder bCryptPasswordEncoder;

    public void addDefaultRoles() {
        List<Profil> rList = utilisateurService.getAllProfil();
        if (rList == null || rList.size() <= 0 ) {
            Profil[] profils = {
                new Profil(null, "STANDARD")
            };
            System.out.println(utilisateurService.addAllProfils(Arrays.asList(profils)));
        }
    }

    public void addDefaultSuperAdmin() {
        Profil profil = utilisateurService.getProfilByLibelle("STANDARD");
        List<Utilisateur> users = utilisateurService.getUsersByProfil("STANDARD");
        if (profil != null && users.size() <= 0) {
            Utilisateur defaultUser = new Utilisateur(
                null, "Mouhamed", "NDOYE", "ndoyeahmed2602@gmail.com",
                bCryptPasswordEncoder.encode("admin@passer"), profil
            );
            utilisateurService.addUser(defaultUser);
            System.out.println("default admin added successfully");
        }
    }
}
