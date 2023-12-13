package com.example.spaceinvaders;

public class User {
    private String nickname;
    private String count;
    public User() {

    }
    public User(String nickname, String count) {
        this.nickname = nickname;
        this.count = count;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}