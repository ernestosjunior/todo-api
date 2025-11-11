package com.uneb.todo.service;

import com.uneb.todo.model.User;
import com.uneb.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public boolean login(String email, String password) {
        Optional<User> user = repository.findByEmail(email);
        return user.isPresent() && encoder.matches(password, user.get().getPassword());
    }
}