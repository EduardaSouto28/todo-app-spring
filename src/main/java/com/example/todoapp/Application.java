package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = {
//        "br.unicap.si.aos.springboot.model"
//})
//@EnableJpaRepositories(basePackages = {
//		"com.example.todoapp.repository"
//})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
