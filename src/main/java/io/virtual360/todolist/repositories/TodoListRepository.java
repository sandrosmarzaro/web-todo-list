package io.virtual360.todolist.repositories;


import io.virtual360.todolist.models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, String> {

}
