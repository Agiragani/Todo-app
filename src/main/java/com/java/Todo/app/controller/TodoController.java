package com.java.Todo.app.controller;

import com.java.Todo.app.dto.TodoDto;
import com.java.Todo.app.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {


    private TodoService todoService;

//    //build add todo rest api
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("hi!");
    }

    //build Get todo rest api
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){

        TodoDto todoDto = todoService.getTodo(todoId);

        if (todoDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // GetAll Todo Rest Api
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // build TodoUpdate Rest APi
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
      TodoDto updatedTodo =   todoService.updateTodo(todoDto, todoId);
      return ResponseEntity.ok(updatedTodo);
    }

    // build todoDelete Rest api
    @DeleteMapping({"{id}"})
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Deleted successfully!. ");
    }

    // build complete todo rest api
@PatchMapping({"{id}/complete"})
    public ResponseEntity<TodoDto> completedTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // build incomplete todo rest api

    @PatchMapping({"{id}/incomplete"})
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.incompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
}
