package com.example.integradorii.estructura;

import com.google.gson.annotations.SerializedName;

public class Race {
    @SerializedName("id_race")
    private int idRace;

    @SerializedName("name")
    private String name;

    public Race(int idRace, String name) {
        this.idRace = idRace;
        this.name = name;
    }

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
