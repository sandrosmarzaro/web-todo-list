package io.virtual360.todolist.controllers;

import io.virtual360.todolist.models.TodoList;
import io.virtual360.todolist.repositories.TodoListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TodoListController {

    private final TodoListRepository todoListRepository;

    public TodoListController(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @GetMapping("/")
    public ModelAndView getLists() {
        List<TodoList> todoLists = todoListRepository.findAll();
        return new ModelAndView("index", "todoLists", todoLists);
    }

    @PostMapping("/todoList")
    public String createList(TodoList todoList) {
        todoListRepository.save(todoList);
        return "redirect:/";
    }

    @GetMapping("/todoList/edit/{id}")
    public ModelAndView editList(@PathVariable Integer id) {
        return todoListRepository.findById(id)
                .map(todoList -> new ModelAndView("edit-todo-list", "todoList", todoList))
                .orElseGet(() -> {
                    String errorMessage = "TodoList with ID " + id + " not found";
                    return new ModelAndView("edit-todo-list", "errorMessage", errorMessage);
                });
    }

    @PostMapping("/todoList/edit/{id}")
    public String updateList(@PathVariable Integer id, TodoList todoList) {
        todoListRepository.findById(id)
                .ifPresent(list -> {
                    list.setName(todoList.getName());
                    list.setDescription(todoList.getDescription());
                    todoListRepository.save(list);
                });
        return "redirect:/";
    }

    @GetMapping("/todoList/remove/{id}")
    public String deleteList(@PathVariable Integer id) {
        todoListRepository.findById(id)
                .ifPresent(todoListRepository::delete);
        return "redirect:/";
    }
}
