package com.uneb.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos_auditoria")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @Column(nullable = false)
    private String operation; // 'I', 'U', 'D'

    @Column(nullable = false)
    private LocalDateTime operationTime;

    @Column(nullable = false)
    private String userName;

    private Long todoId;
    private String title;
    private String description;
    private String priority;
    private String status;
    private LocalDate deadline;
    private Long userId;
}
