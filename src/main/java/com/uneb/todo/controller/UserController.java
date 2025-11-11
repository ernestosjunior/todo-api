package com.uneb.todo.controller;

import com.uneb.todo.model.User;
import com.uneb.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        boolean success = service.login(email, password);
        if (success)
            return ResponseEntity.ok(Map.of("message", "Login successful!"));
        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }
}