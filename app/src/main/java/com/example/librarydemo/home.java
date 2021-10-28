package com.example.librarydemo;

import java.io.Serializable;

public class home implements Serializable {
    private int image;
    private String name;

    public home(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
