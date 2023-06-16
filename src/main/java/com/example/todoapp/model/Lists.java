package com.example.todoapp.model;

import java.util.List;

import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name="list")
public class Lists {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity=Item.class, fetch=FetchType.EAGER)
    @JoinColumn(name="itemId")
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public List<Item> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
