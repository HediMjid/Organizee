package fr.organizee.repository;

import fr.organizee.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

@Query(value = "select * from contact where team_id = :team_id", nativeQuery = true)
    List<Contact> FindContactsByTeam(@Param("team_id") int team_id);


}
