package io.virtual360.todolist.controllers;

import io.virtual360.todolist.models.TodoList;
import io.virtual360.todolist.repositories.TodoListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todoList")
public class TodoListController {

    private final TodoListRepository todoListRepository;

    public TodoListController(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @GetMapping
    public ResponseEntity<List<TodoList>> getLists() {
        return ResponseEntity.ok(todoListRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoList> gelListById(@PathVariable Integer id) {
        return todoListRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TodoList> createList(@RequestBody TodoList todoList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoListRepository.save(todoList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoList> updateList(@PathVariable Integer id, @RequestBody TodoList todoList) {
        return todoListRepository.findById(id)
                .map(list -> {
                    list.setName(todoList.getName());
                    list.setDescription(todoList.getDescription());
                    return ResponseEntity.ok(todoListRepository.save(list));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteList(@PathVariable Integer id) {
        return todoListRepository.findById(id)
                .map(list -> {
                    todoListRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoList> partiallyUpdateTodoList(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        return todoListRepository.findById(id)
                .map(existingTodoList -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name" -> existingTodoList.setName((String) value);
                            case "description" -> existingTodoList.setDescription((String) value);
                            default -> ResponseEntity.badRequest().build();
                        }
                    });
                    TodoList savedTodoList = todoListRepository.save(existingTodoList);
                    return ResponseEntity.ok(savedTodoList);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
