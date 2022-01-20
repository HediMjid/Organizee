package fr.organizee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.organizee.model.Membre;
import fr.organizee.repository.MembreRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MembreRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        final Optional<Membre> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("utilisateur '" + email + "' introuvable");
        }

        return User
                .withUsername(email)
                .password(user.get().getPassword())
                .authorities(user.get().getRoleList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}

