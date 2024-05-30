package com.example.integradorii.estructura;

import java.util.Date;

public class Vacuna {
    private String name;
    private String description;
    private String next_vaccine;
    private String status;
    private String id_pet;
    private String created_at;

    public Vacuna(String name, String description, String next_vaccine, String status, String created_at, String id_pet) {
        this.name = name;
        this.description = description;
        this.next_vaccine = next_vaccine;
        this.status = status;
        this.created_at = created_at;
        this.id_pet = id_pet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNext_vaccine() {
        return next_vaccine;
    }

    public void setNext_vaccine(String next_vaccine) {
        this.next_vaccine = next_vaccine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId_pet() {
        return id_pet;
    }

    public void setId_pet(String id_pet) {
        this.id_pet = id_pet;
    }
}
