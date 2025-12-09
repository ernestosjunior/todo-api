package com.uneb.todo.controller;

import com.uneb.todo.model.Todo;
import com.uneb.todo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Todo todo) {
        try {
            Todo _todo = service.create(todo);
            return ResponseEntity.ok(_todo);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body(null);
        }

    }

    @GetMapping
    public List<Todo> list() {
        return service.list();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updatePartial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Todo updatedTodo = service.partialUpdate(id, updates);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

}
