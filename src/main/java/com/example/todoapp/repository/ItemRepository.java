package com.example.todoapp.repository;
import com.example.todoapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
