package com.example.administrator.fellingmenu.bean;

/**
 * Created by Administrator on 2015/4/14.
 */
public class HotMenu {
    private String imagePath;
    private String text;

    public HotMenu(String text, String imagePath) {
        this.text = text;
        this.imagePath = imagePath;
    }

    public HotMenu() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "HotMenu{" +
                "imagePath='" + imagePath + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
