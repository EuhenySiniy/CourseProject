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

    public void setUserFio(String userFio) {
        this.userFio = userFio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
