package com.example.todoapp.controller;

import com.example.todoapp.model.Item;
import com.example.todoapp.model.Lists;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Recupera todos os usuários
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Cria um novo usuário
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Autentica um usuário
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User storedUser = userRepository.findByUsername(user.getName());
        if (storedUser != null && storedUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Excluir um usuário
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
