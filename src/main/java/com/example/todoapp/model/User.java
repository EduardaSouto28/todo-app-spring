package com.example.todoapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID user_id;

    private String name;

    private String password;

    @OneToMany(targetEntity=Item.class, fetch=FetchType.EAGER)
    @JoinColumn(name="list_id")
    private List<Lists> lists;

    public UUID getId() {
        return user_id;
    }

    public void setId(UUID id) {
        this.user_id = id;
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
