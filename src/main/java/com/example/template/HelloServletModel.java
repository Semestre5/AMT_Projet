package com.example.template;

import java.io.Serializable;

public class HelloServletModel implements Serializable {
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}