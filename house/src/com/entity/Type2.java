package com.entity;

import java.io.Serializable;

public class Type2 implements Serializable{
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Type2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}