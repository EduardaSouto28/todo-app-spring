package com.example.todoapp.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;


@Entity
@Table(name="todo_items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isChecked;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private Lists lists;

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}