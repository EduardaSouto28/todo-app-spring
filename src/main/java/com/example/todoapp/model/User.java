package com.example.todoapp.model;

import java.util.List;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;

    @OneToMany(targetEntity=Item.class, fetch=FetchType.EAGER)
    @JoinColumn(name="list_id")
    private List<Lists> lists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Lists> getLists() {
        return lists;
    }

}