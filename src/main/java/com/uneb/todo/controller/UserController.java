package com.uneb.todo.controller;

import com.uneb.todo.model.User;
import com.uneb.todo.service.JwtService;
import com.uneb.todo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User _user = service.register(user);
            return ResponseEntity.ok(_user);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body(null);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            String password = body.get("password");

            User user = service.login(email, password);
            String token = jwtService.generateToken(user);

            System.out.println(token);

            return ResponseEntity.ok(
                    Map.of(
                            "token", token,
                            "user", user));
        } catch (RuntimeException e) {
            System.out.println(e);
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
}