package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.example.todoapp.model"
})
@EnableJpaRepositories(basePackages = {
		"com.example.todoapp.repository"
})
public class TodoAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

}
