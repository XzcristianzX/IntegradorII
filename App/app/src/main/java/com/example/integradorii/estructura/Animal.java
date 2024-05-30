package com.example.integradorii.estructura;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Animal {
    @SerializedName("id_animal")
    private int idAnimal;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("race")
    private String race;

    @SerializedName("location")
    private String location;

    @SerializedName("owner")
    private String owner;

    @SerializedName("weight")
    private String weight;

    @SerializedName("size")
    private String size;

    @SerializedName("gender")
    private String gender;

    @SerializedName("img")
    private String img;

    @SerializedName("birthdate")
        private String birthdate;

    @SerializedName("active")
    private boolean active;

    public Animal( String name, String type, String race, String location, String owner, String weight, String size, String gender, String img, String birthdate, boolean active) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.type = type;
        this.race = race;
        this.location = location;
        this.owner = owner;
        this.weight = weight;
        this.size = size;
        this.gender = gender;
        this.img = img;
        this.birthdate = birthdate;
        this.active = active;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

            public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
