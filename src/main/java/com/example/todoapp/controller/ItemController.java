package com.example.todoapp.controller;

import com.example.todoapp.model.Item;
import com.example.todoapp.model.Lists;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.ItemRepository;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    // Recupera os itens de uma lista específica de um usuário específico
    @GetMapping("/{userId}/list/{listId}/items")
    public ResponseEntity<List<Item>> getAllItemsByListAndUser(@PathVariable UUID userId, @PathVariable UUID listId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<Lists> lists = user.getLists();
        Lists targetList = lists.stream()
                .filter(list -> list.getId().equals(listId))
                .findFirst()
                .orElse(null);
        if (targetList == null) {
            return ResponseEntity.notFound().build();
        }
        List<Item> items = targetList.getItems();
        return ResponseEntity.ok(items);
    }

    // Cria um item em uma lista específica de um usuário específico
    @PostMapping("/{userId}/list/{listId}/item")
    public ResponseEntity<Item> createItemInListForUser(@PathVariable UUID userId, @PathVariable UUID listId, @RequestBody Item item) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<Lists> lists = user.getLists();
        Lists targetList = lists.stream()
                .filter(list -> list.getId().equals(listId))
                .findFirst()
                .orElse(null);
        if (targetList == null) {
            return ResponseEntity.notFound().build();
        }
        com.example.todoapp.model.Lists targetLists = lists.stream()
                .filter(list -> list.getId().equals(listId))
                .findFirst()
                .orElse(null);
        if (targetLists == null) {
            return ResponseEntity.notFound().build();
        }
        item.setLists(targetList);
        targetList.getItems().add(item);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    // Excluir um item de uma lista específica de um usuário específico
    @DeleteMapping("/{userId}/list/{listId}/item/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID userId, @PathVariable UUID listId, @PathVariable UUID itemId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<Lists> lists = user.getLists();
        Lists targetList = lists.stream()
                .filter(list -> list.getId().equals(listId))
                .findFirst()
                .orElse(null);
        if (targetList == null) {
            return ResponseEntity.notFound().build();
        }
        List<Item> items = targetList.getItems();
        Item itemToRemove = items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
        if (itemToRemove == null) {
            return ResponseEntity.notFound().build();
        }
        items.remove(itemToRemove);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    // Atualiza o status de isChecked de um item em uma lista específica de um usuário específico
    @PutMapping("/{userId}/list/{listId}/item/{itemId}")
    public ResponseEntity<Item> updateItemStatus(@PathVariable UUID userId, @PathVariable UUID listId, @PathVariable UUID itemId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<Lists> lists = user.getLists();
        Lists targetList = lists.stream()
                .filter(list -> list.getId().equals(listId))
                .findFirst()
                .orElse(null);
        if (targetList == null) {
            return ResponseEntity.notFound().build();
        }
        List<Item> items = targetList.getItems();
        Item targetItem = items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
        if (targetItem == null) {
            return ResponseEntity.notFound().build();
        }
        targetItem.setChecked(!targetItem.isChecked()); // Inverte o valor do isChecked
        userRepository.save(user);
        return ResponseEntity.ok(targetItem);
    }

}
