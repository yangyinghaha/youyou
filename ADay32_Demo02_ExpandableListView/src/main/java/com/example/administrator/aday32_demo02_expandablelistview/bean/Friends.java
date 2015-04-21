package com.example.administrator.aday32_demo02_expandablelistview.bean;

/**
 * Created by Administrator on 2015/4/9.
 */
public class Friends {
    private int groupId;
    private String name;
    private String  numb;

    public Friends(int groupId, String name, String numb) {
        this.groupId = groupId;
        this.name = name;
        this.numb = numb;
    }

    public Friends() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", numb='" + numb + '\'' +
                '}';
    }
}
