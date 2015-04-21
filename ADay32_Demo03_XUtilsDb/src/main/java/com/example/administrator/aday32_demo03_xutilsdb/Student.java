package com.example.administrator.aday32_demo03_xutilsdb;

/**
 * Created by Administrator on 2015/4/9.
 */
public class Student {
    private int _id;
    private String name;
    private int age;
    private int score;
    private int clas;
    private int num;

    public Student(int _id,String name, int age, int clas, int score, int num) {
        this._id=_id;
        this.name = name;
        this.age = age;
        this.clas = clas;
        this.score = score;
        this.num = num;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getClas() {
        return clas;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
