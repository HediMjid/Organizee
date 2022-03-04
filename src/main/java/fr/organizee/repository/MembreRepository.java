package fr.organizee.repository;

import fr.organizee.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Integer> {
    Membre findByNom(String nom);

    Optional<Membre> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
