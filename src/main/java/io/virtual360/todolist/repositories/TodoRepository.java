package io.virtual360.todolist.repositories;

import io.virtual360.todolist.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, String> {
}
