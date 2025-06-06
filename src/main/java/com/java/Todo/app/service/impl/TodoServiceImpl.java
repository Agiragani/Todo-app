package com.java.Todo.app.service.impl;

import com.java.Todo.app.dto.TodoDto;
import com.java.Todo.app.entity.Todo;
import com.java.Todo.app.repository.TodoRepository;
import com.java.Todo.app.service.TodoService;
import com.sun.source.tree.TypeParameterTree;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert todoDto into todoJpaEntity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());

        Todo todo = modelMapper.map(todoDto, Todo.class);

        // convert Jpa entity

        Todo savedTodo = todoRepository.save(todo);


        //convert saved Todo Jpa entity object into TodoDto object

//        TodoDto savedTodoDto = new TodoDto();

//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
       Todo todo = todoRepository.findById(id).get();


        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo)-> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override

    public TodoDto updateTodo(TodoDto todoDto, Long id) {
       Todo todo = todoRepository
                .findById(id).orElseThrow(()->
                        new ResourceAccessException("Todo not found with id" + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo =   todoRepository.findById(id).orElseThrow(()->
                new ResourceAccessException("Todo not found with id" + id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo =   todoRepository.findById(id).orElseThrow(()->
                new ResourceAccessException("Todo not found with id" + id));
        todo.setCompleted(Boolean.TRUE);
       Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo =   todoRepository.findById(id).orElseThrow(()->
                new ResourceAccessException("Todo not found with id" + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }


}
