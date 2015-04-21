package com.example.administrator.aday32_demo02_expandablelistview.bean;

/**
 * Created by Administrator on 2015/4/9.
 */
public class Groups {
    private int id;
    private String name;

    public Groups(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Groups() {
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

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
