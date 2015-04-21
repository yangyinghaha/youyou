package com.example.bean;

/**
 * Created by Administrator on 2015/4/2.
 */
public class Y18Second {
    private String title;
    private String img;
    private String keywords;
    private  String from;

    public Y18Second(String title, String img, String keywords, String from) {
        this.title = title;
        this.img = img;
        this.keywords = keywords;
        this.from = from;
    }

    public Y18Second() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
