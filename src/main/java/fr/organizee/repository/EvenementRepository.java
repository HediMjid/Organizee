package fr.organizee.repository;

import fr.organizee.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Integer> {
    @Query(value = "select * from evenement where team_id = :team_id", nativeQuery = true)
    List<Evenement> FindEvenementsByTeam(@Param("team_id") int team_id);
}
