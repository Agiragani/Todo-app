package com.java.Todo.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity                     // To mention this todo class has jpa entity
@Table(name = "Todos")
public class Todo {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "Name of action", nullable = false)
    private String description;
    @Column(name = "Status")
    private boolean completed;
}
