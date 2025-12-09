package com.uneb.todo.service;

import com.uneb.todo.model.Todo;
import com.uneb.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Todo create(Todo todo) {
        return repository.save(todo);
    }

    public Optional<Todo> getById(Long id) {
        Optional<Todo> todo = repository.findById(id);
        return todo;
    }

    public List<Todo> list() {
        List<Todo> todos = repository.findAll();
        return todos;
    }

    public Todo update(Todo updatedTodo) {
        return repository.updateTodo(updatedTodo);
    }

    public void delete(Long id) {
        repository.deleteTodoById(id);
    }

    public Todo partialUpdate(Long id, Map<String, Object> updates) {
        return repository.partialUpdate(id, updates);
    }
}
