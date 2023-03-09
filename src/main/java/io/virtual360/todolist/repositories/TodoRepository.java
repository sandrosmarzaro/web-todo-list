package io.virtual360.todolist.repositories;

import io.virtual360.todolist.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("SELECT t FROM Todo t WHERE t.todoList.id = :todoListId")
    List<Todo> findByTodoListId(Integer todoListId);
}
