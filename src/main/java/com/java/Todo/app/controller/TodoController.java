package com.java.Todo.app.controller;

import com.java.Todo.app.dto.TodoDto;
import com.java.Todo.app.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/todos")
@AllArgsConstructor


public class TodoController {

//    @RequestMapping("api/ping")
////    public String ping() {
//        return "Hi";
//    }

    private TodoService todoService;

//    //build add todo rest api
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
}
