package com.example.integradorii.estructura;

import com.google.gson.annotations.SerializedName;

public class Careful {
    @SerializedName("id_careful")
    private int idCareful;

    @SerializedName("feeding")
    private String feeding;

    @SerializedName("bathroom")
    private String bathroom;

    @SerializedName("race")
    private Race race;

    @SerializedName("activity")
    private Actividad activity;

    public Careful(int idCareful, String feeding, String bathroom, Race race, Actividad activity) {
        this.idCareful = idCareful;
        this.feeding = feeding;
        this.bathroom = bathroom;
        this.race = race;
        this.activity = activity;
    }

    // Getters y setters
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

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Actividad getActivity() {
        return activity;
    }

    public void setActivity(Actividad activity) {
        this.activity = activity;
    }
}
