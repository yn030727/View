package com.example.rxjava;

public class Swordsman {
    private String name;
    private String level;
    private int Age;

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Swordsman(String name , String level){
        this.name = name ;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
