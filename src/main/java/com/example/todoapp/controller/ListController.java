package com.example.todoapp.controller;

import com.example.todoapp.model.Lists;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.ListRepository;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private UserRepository userRepository;

    // Recupera todas as listas de um usuário específico
    @GetMapping("/{userId}/lists")
    public ResponseEntity<List<Lists>> getAllListsByUser(@PathVariable UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Lists> lists = user.getLists();
        return ResponseEntity.ok(lists);
    }

    // Cria uma lista para um usuário específico
    @PostMapping("/{userId}/list")
    public ResponseEntity<Lists> createListForUser(@PathVariable UUID userId, @RequestBody Lists list) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        list.setUser(user);
        user.getLists().add(list);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    // Excluir uma lista de um usuário específico
    @DeleteMapping("/{userId}/list/{listId}")
    public ResponseEntity<Void> deleteList(@PathVariable UUID userId, @PathVariable UUID listId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Lists> lists = user.getLists();
        Lists listToRemove = null;
        for (Lists list : lists) {
            if (list.getId().equals(listId)) {
                listToRemove = list;
                break;
            }
        }
        if (listToRemove == null) {
            return ResponseEntity.notFound().build();
        }
        lists.remove(listToRemove);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}


