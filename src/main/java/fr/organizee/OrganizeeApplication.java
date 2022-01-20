package fr.organizee;

import fr.organizee.model.Membre;
import fr.organizee.model.Role;
import fr.organizee.service.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class OrganizeeApplication implements CommandLineRunner {

	@Autowired
	private MembreService membreService;

	public static void main(String[] args) {
		SpringApplication.run(OrganizeeApplication.class, args);
	}

	/**
	 * Ceci est un Bean, un composant
	 * Méthode de Hachage
	 * Bcrypt est un algorithme de hachage considé comme le plus sûr.
	 * bcrypt est un algorithme de hashage unidirectionnel,
	 * vous ne pourrez jamais retrouver le mot de passe sans connaitre Ã  la fois le grain de sel,
	 * la clé et les différentes passes que l'algorithme Ã  utiliser.
	 * Voir le <a href="https://bcrypt-generator.com/"> site pour effectuer un test</a>
	 *
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

