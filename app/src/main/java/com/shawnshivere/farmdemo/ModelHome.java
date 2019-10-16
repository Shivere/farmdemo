package com.shawnshivere.farmdemo;

public class ModelHome {
    private int thumbnail;
    private String name, desc;

    public ModelHome(){

    }

    public ModelHome(int thumbnail, String name, String desc){
        this.thumbnail = thumbnail;
        this.name = name;
        this.desc = desc;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
