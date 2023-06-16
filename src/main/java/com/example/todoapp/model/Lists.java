package com.example.todoapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.util.UUID;

@Entity
public class Lists {
    @Id
    @GeneratedValue
    private UUID list_id;

    private String name;

    @ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity=Item.class, fetch=FetchType.EAGER)
    @JoinColumn(name="item_id")
    private List<Item> items;

    public UUID getId() {
        return list_id;
    }

    public void setId(UUID id) {
        this.list_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }
}
