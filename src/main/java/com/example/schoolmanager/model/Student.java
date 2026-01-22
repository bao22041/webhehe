package com.example.schoolmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Integer id; // ✅ id nhập tay theo bảng SQL

    private String name;
    private String email;

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}
