package fr.organizee.repository;

import fr.organizee.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer> {

    // N'est plus utilisé normalement
    @Query(value = "select * from todo_list, tache where todo_list.team_id = :team_id and todo_list.id = tache.todolist_id", nativeQuery = true)
    List<Tache> FindTachesByTeam(@Param("team_id") int team_id);
}
