package com.example.todoapispring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    @Qualifier("fakeTodoService")
    private TodoService todoService;
    private TodoService todoService2;

//    @Bean
//    public TodoService todoService() {
//        return new TodoService();
//    }

    private static List<Todo> todoList;

    public TodoController(
            @Qualifier("anotherTodoService") TodoService todoservice,
            @Qualifier("fakeTodoService") TodoService todoservice2) {
        this.todoService = todoservice;
        this.todoService2 = todoservice2;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));



    }
    @GetMapping("/")
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false, defaultValue = "true") boolean is_completed) {
        System.out.println("Incoming query params: " + is_completed + " " + this.todoService2.addTodo());
        return ResponseEntity.ok(todoList);
    }

    @PostMapping("/")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);

    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable int todoId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TODO_NOT_FOUND");


    }


}
