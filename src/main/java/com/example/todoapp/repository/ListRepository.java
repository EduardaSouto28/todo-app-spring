package com.example.todoapp.repository;
import com.example.todoapp.model.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListRepository extends JpaRepository<Lists, Long> {
}
