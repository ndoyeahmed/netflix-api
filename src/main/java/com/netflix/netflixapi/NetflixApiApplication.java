package com.netflix.netflixapi;

import com.netflix.netflixapi.fixtures.UtilisateurFixtures;
import com.netflix.netflixapi.services.movies.MovieService;
import com.netflix.netflixapi.services.utils.MovieConsumeService;
import com.netflix.netflixapi.services.utils.dto.MovieResponse;
import com.netflix.netflixapi.services.utils.dto.TVShowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NetflixApiApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	private MovieService movieService;
	@Autowired
	private MovieConsumeService movieConsumeService;

	@Autowired
	private UtilisateurFixtures utilisateurFixture;

	public static void main(String[] args) {
		SpringApplication.run(NetflixApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		utilisateurFixture.addDefaultRoles();
		utilisateurFixture.addDefaultSuperAdmin();

		movieService.removeAllMovies();
		movieService.removeAllTVShow();
		MovieResponse movieResponse = movieConsumeService.getTrendingMovie();
		TVShowResponse tvShowResponse = movieConsumeService.getTopRatedTVShow();
		movieService.addAllMovie(movieResponse.getResults());
		movieService.addAllTVShow(tvShowResponse.getResults());
	}
}
