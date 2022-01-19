package fr.organizee.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration de S�curit� globale pour notre REST API.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Methode qui configure la s�curit� HTTP.
     * @param http the HttpSecurity object to configure.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (Cross Site Request Forgery comme votre Token sera stock� dans le session storage)
        http.cors();
        
        http.csrf().disable()
        .authorizeRequests()
          .antMatchers("/**").permitAll() // accessible sans besoin de s'authentifier
          .and()
          .authorizeRequests()
          .antMatchers("/membres/sign-in").permitAll() // se connecter
          .antMatchers("/membres/sign-up").permitAll() // s'inscrire
          .antMatchers("membres/all").hasAuthority("ROLE_PARENT") // uniquement pour le r�le admin
          .anyRequest().authenticated(); // tout le reste est autoris� par un utilisateur authentifi�
        // Appliquer un filtre avec le token pour toutes requ�tes HTTP
        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
       
    }

    /**
     * Methode qui configure la s�curit� web.
     * Utilis� pour interdire l'acc�s à certains r�pertoires.
     * @param web : WebSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}



