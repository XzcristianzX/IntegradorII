package com.example.integradorii.estructura;

import java.util.Date;

public class User {
    private int idUser;
    private String username;
    private String name;
    private String birthdate;
    private String email;
    private String password;
    private String imgProfile;
    private String phone;
    private String gender;
    private boolean active;
    private String code;

    public User(int idUser, String username, String name, String birthdate, String email, String password, String imgProfile, String phone, String gender, boolean active,String code) {
        this.idUser = idUser;
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.imgProfile = imgProfile;
        this.phone = phone;
        this.gender = gender;
        this.active = active;
        this.code = code;
    }

    public User(String email, String code, String password) {
        this.email = email;
        this.code = code;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

