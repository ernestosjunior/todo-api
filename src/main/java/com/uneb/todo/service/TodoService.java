package com.uneb.todo.service;

import com.uneb.todo.model.Todo;
import com.uneb.todo.model.Todo.Status;
import com.uneb.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    public Map<Todo.Status, List<Todo>> getBoard() {

        List<Todo> todos = repository.findAllByOrderByStatusAscOrdAsc();

        Map<Todo.Status, List<Todo>> board = new LinkedHashMap<>();

        for (Todo.Status status : Todo.Status.values()) {
            board.put(status, new ArrayList<>());
        }

        for (Todo todo : todos) {
            board.get(todo.getStatus()).add(todo);
        }

        return board;
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
