package com.example.rxjava;

public class User {
    private String name;
    private int followers;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowers() {
        return followers;
    }
}
