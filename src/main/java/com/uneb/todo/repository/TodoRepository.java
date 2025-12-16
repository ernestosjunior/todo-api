package com.uneb.todo.repository;

import com.uneb.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findById(Long id);

    default Todo updateTodo(Todo updatedTodo) {
        return save(updatedTodo);
    }

    List<Todo> findAllByOrderByStatusAscOrdAsc();

    default void deleteTodoById(Long id) {
        deleteById(id);
    }

    default Todo partialUpdate(Long id, Map<String, Object> updates) {
        Todo todo = findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    todo.setTitle((String) value);
                    break;
                case "description":
                    todo.setDescription((String) value);
                    break;
                case "priority":
                    todo.setPriority(Todo.Priority.valueOf((String) value));
                    break;
                case "status":
                    todo.setStatus(Todo.Status.valueOf((String) value));
                    break;
                case "deadline":
                    todo.setDeadline(LocalDate.parse((String) value));
                    break;
                case "userId":
                    if (value instanceof Number number) {
                        todo.setUserId(number.longValue());
                    }
                    break;
                case "ord":
                    todo.setOrd((int) value);
                    break;
                default:
                    throw new RuntimeException("Campo inválido: " + key);
            }
        });

        return save(todo);
    }
}