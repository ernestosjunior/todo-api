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
        user.setSenha(encoder.encode(user.getSenha()));
        return repository.save(user);
    }

    public boolean login(String email, String passoword) {
        Optional<User> user = repository.findByEmail(email);
        return user.isPresent() && encoder.matches(passoword, user.get().getSenha());
    }
}