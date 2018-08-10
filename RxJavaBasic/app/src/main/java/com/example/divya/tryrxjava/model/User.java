package com.example.divya.tryrxjava.model;

public class User {

    public User(int id, String username)
    {
        this.setId(id);
        this.setName(username);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;


}
