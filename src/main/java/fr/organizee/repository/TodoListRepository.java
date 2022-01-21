package fr.organizee.repository;

import fr.organizee.model.Menu;
import fr.organizee.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
    @Query(value = "select * from todo_list where team_id = :team_id", nativeQuery = true)
    List<TodoList> FindTodolistByTeam(@Param("team_id") int team_id);
}
