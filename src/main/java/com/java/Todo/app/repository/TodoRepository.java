package com.java.Todo.app.repository;

import com.java.Todo.app.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository
        extends JpaRepository<Todo, Long> {  // helps to perform crud operations on entity through jpaRepository


}
