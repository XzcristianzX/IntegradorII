package com.example.integradorii.estructura;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_user")
    private int idUser;


    @SerializedName("user_name")
    private String userName;

    @SerializedName("name")
    private String name;

    @SerializedName("birthdate")
    private String birthdate;

    @SerializedName("mail")
    private String mail;

    @SerializedName("password")
    private String password;

    @SerializedName("img_profile")
    private String img;

    @SerializedName("phone")
    private String phone;

    @SerializedName("gender")
    private String gender;

    public User(int idUser, String userName, String name, String birthdate, String mail, String password, String img, String phone, String gender) {
        this.idUser = idUser;
        this.userName = userName;
        this.name = name;
        this.birthdate = birthdate;
        this.mail = mail;
        this.password = password;
        this.img = img;
        this.phone = phone;
        this.gender = gender;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
}
