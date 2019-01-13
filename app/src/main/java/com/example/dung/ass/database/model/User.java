package com.example.dung.ass.database.model;

public class User {
    public String id;
    public String name;

    public User(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String phone;
}
