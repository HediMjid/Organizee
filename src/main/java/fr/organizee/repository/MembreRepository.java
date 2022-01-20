package fr.organizee.repository;

import fr.organizee.model.Contact;
import fr.organizee.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Integer> {
    Membre findByNom(String nom);

    Optional<Membre> findByEmail(String email);

    @Query(value = "select * from membre where email = :email", nativeQuery = true)
    Membre chercheEmail(@Param("email") String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);


}
