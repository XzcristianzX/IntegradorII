package com.example.integradorii.estructura;

import com.google.gson.annotations.SerializedName;

public class Careful {
    @SerializedName("id_careful")
    private int idCareful;

    @SerializedName("feeding")
    private String feeding;

    @SerializedName("bathroom")
    private String bathroom;

    @SerializedName("id_vaccine")
    private int idVaccine;

    @SerializedName("id_activity")
    private int idActivity;

    public Careful(int idCareful, String feeding, String bathroom, int idVaccine, int idActivity) {
        this.idCareful = idCareful;
        this.feeding = feeding;
        this.bathroom = bathroom;
        this.idVaccine = idVaccine;
        this.idActivity = idActivity;
    }

    public int getIdCareful() {
        return idCareful;
    }

    public void setIdCareful(int idCareful) {
        this.idCareful = idCareful;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public int getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(int idVaccine) {
        this.idVaccine = idVaccine;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }
}
