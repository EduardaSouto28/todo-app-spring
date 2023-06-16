package com.example.todoapp.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
public class Item {
    @Id
    @GeneratedValue
    private UUID item_id;

    private String name;

    private boolean isChecked;

    @ManyToOne(targetEntity=List.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "list_id")
    private Lists lists;

    public UUID getId() {
        return item_id;
    }

    public void setId(UUID id) {
        this.item_id = id;
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

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }
}
