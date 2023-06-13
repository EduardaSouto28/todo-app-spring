package com.example.todoapp.controller;

import com.example.todoapp.model.Lists;
import com.example.todoapp.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/list")
public class ListController {
    private final ListRepository listRepository;

    @Autowired
    public ListController(ListRepository listsRepository) {
        this.listRepository = listsRepository;
    }

    @GetMapping
    public ResponseEntity<List<Lists>> getAllLists() {
        List<Lists> lists = listRepository.findAll();
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lists> createList(@RequestBody Lists newList) {
        Lists createdList = listRepository.save(newList);
        return new ResponseEntity<>(createdList, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable UUID id) {
        listRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


