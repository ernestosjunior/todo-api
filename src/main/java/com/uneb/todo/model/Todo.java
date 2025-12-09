package com.uneb.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "todos")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        URGENT
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    public enum Status {
        TODO,
        IN_PROGRESS,
        COMPLETED,
        CANCELED
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = true)
    private Long userId;
}
