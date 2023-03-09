package io.virtual360.todolist.controllers;

import io.virtual360.todolist.models.Todo;
import io.virtual360.todolist.models.TodoList;
import io.virtual360.todolist.repositories.TodoListRepository;
import io.virtual360.todolist.repositories.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TodoController {

    private final TodoRepository todoRepository;
    private final TodoListRepository todoListRepository;

    public TodoController(TodoRepository todoRepository, TodoListRepository todoListRepository) {
        this.todoRepository = todoRepository;
        this.todoListRepository = todoListRepository;
    }

    @GetMapping("/todos/{id}")
    public ModelAndView index(@PathVariable("id") Integer id) {
        List<Todo> todos = todoRepository.findByTodoListId(id);
        TodoList todoList = todoListRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("todos");
        modelAndView.addObject("todoList", todoList);
        modelAndView.addObject("todos", todos);
        return modelAndView;
    }

    @PostMapping("/todos")
    public String create(String name,
                         String description,
                         Integer todoListId) {
        Todo todo = new Todo();
        todo.setName(name);
        todo.setDescription(description);
        todo.setTodoList(todoListRepository.findById(todoListId).get());
        todoRepository.save(todo);

        return "redirect:/todos/" + todoListId;
    }

    @GetMapping("/todos/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id) {
        Todo todo = todoRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("edit-todo");
        modelAndView.addObject("todo", todo);
        return modelAndView;
    }

    @PostMapping("/todos/edit/{id}")
    public String update(@PathVariable("id") Integer id,
                         String name,
                         String description,
                         Integer todoListId) {

        System.out.println("id: " + id);
        System.out.println("name: " + name);
        System.out.println("description: " + description);
        System.out.println("todoListId: " + todoListId);

        Todo todo = todoRepository.findById(id).get();
        todo.setName(name);
        todo.setDescription(description);
        todo.setTodoList(todoListRepository.findById(todoListId).get());
        todoRepository.save(todo);
        return "redirect:/todos/" + todoListId;
    }

    @GetMapping("/todos/remove/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Todo todo = todoRepository.findById(id).get();
        todoRepository.delete(todo);
        return "redirect:/todos/" + todo.getTodoList().getId();
    }
}
