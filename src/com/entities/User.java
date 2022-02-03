package com.entities;

public class User {
    private String userFio;
    private String email;
    private String tel;

    public User(String userFio,
                String email,
                String tel) {
        this.userFio = userFio;
        this.email = email;
        this.tel = tel;
    }

    public String getUserFio() {
        return userFio;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }
}
