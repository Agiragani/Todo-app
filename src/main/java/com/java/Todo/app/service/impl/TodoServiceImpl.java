package com.java.Todo.app.service.impl;

import com.java.Todo.app.dto.TodoDto;
import com.java.Todo.app.entity.Todo;
import com.java.Todo.app.repository.TodoRepository;
import com.java.Todo.app.service.TodoService;
import com.sun.source.tree.TypeParameterTree;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert todoDto into todoJpaEntity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        // convert Jpa entity

        Todo savedTodo = todoRepository.save(todo);


        //convert saved Todo Jpa entity object into TodoDto object

        TodoDto savedTodoDto = new TodoDto();

        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());
        return savedTodoDto;
    }
}
