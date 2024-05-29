package com.example.integradorii.estructura;

public class User {
    private int userId;
    private String user_name;
    private String name;
    private String birthdate;
    private String mail;
    private String password;
    private String imgProfile;
    private String phone;
    private String gender;
    private boolean active;
    private String code;

    public User(String username, String name, String birthdate, String email, String password, String phone, String gender, boolean active) {
        this.user_name = username;
        this.name = name;
        this.birthdate = birthdate;
        this.mail = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.active = active;
    }

    public User(String email, String code, String password) {
        this.mail = email;
        this.code = code;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

