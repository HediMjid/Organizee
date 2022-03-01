package fr.organizee.repository;

import fr.organizee.model.Contact;
import fr.organizee.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository <Menu, Integer> {

    @Query(value = "select * from menu where team_id = :team_id", nativeQuery = true)
    List<Menu> FindMenusByTeam(@Param("team_id") int team_id);

}
