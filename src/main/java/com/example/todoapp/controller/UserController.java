package com.example.todoapp.controller;

import com.example.todoapp.model.User;
import com.example.todoapp.model.Item;
import com.example.todoapp.model.Lists;
import com.example.todoapp.repository.ItemRepository;
import com.example.todoapp.repository.ListRepository;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ItemRepository itemRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Recupera todos os usuários
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Cria um novo usuário
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Autentica um usuário
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User authenticatedUser = userRepository.findByNameAndPassword(user.getName(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Excluir um usuário
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    // Recupera todas as listas de um usuário específico
    @GetMapping("/{userId}/lists")
    public List<Lists> getUserLists(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getLists();
        } else {
            return Collections.emptyList();
        }
    }

    // Cria uma lista para um usuário específico
    @PostMapping("/{userId}/list")
    public Lists createList(@PathVariable Long userId, @RequestBody Lists list) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            list.setUser(user);
            return listRepository.save(list);
        } else {
            return null;
        }
    }

    // Excluir uma lista de um usuário específico
    @DeleteMapping("/{userId}/list/{listId}")
    public ResponseEntity<Void> deleteList(@PathVariable Long userId, @PathVariable Long listId) {
        Lists list = userRepository.findListByIdAndUserId(listId, userId);
        if (list != null) {
            listRepository.delete(list);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Recupera os itens de uma lista específica de um usuário específico
    @GetMapping("/{userId}/list/{listId}/items")
    public List<Item> getListItems(@PathVariable Long userId, @PathVariable Long listId) {
        Lists list = userRepository.findListByIdAndUserId(listId, userId);
        if (list != null) {
            return list.getItems();
        } else {
            return Collections.emptyList();
        }
    }

    // Cria um item em uma lista específica de um usuário específico
    @PostMapping("/{userId}/list/{listId}/item")
    public Item createItem(@PathVariable Long userId, @PathVariable Long listId, @RequestBody Item item) {
        Lists list = userRepository.findListByIdAndUserId(listId, userId);
        if (list != null) {
            item.setLists(list);
            return itemRepository.save(item);
        } else {
            return null;
        }
    }

//    // Excluir um item de uma lista específica de um usuário específico
//    @DeleteMapping("/{userId}/list/{listId}/item/{itemId}")
//    public ResponseEntity<Void> deleteItem(@PathVariable Long userId, @PathVariable Long listId, @PathVariable Long itemId) {
//        Item item = userRepository.findItemByIdAndListIdAndUserId(itemId, listId, userId);
//        if (item != null) {
//            itemRepository.delete(item);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

