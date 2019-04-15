package com.springsecurity.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_power")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String path;
    private String role;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
