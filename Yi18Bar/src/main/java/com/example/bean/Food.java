package com.example.bean;

/**
 * Created by Administrator on 2015/4/2.
 */
public class Food {
    private String name;
    private String image;

    public Food() {
    }

    public Food(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
