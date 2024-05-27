package com.example.integradorii.estructura;

public class Actividad {
    private int idActivity;
    private String name;
    private String durationTime;
    private String implementacion;

    public Actividad() {

    }

    // Constructor
    public Actividad(int idActivity, String name, String durationTime, String implementacion) {
        this.idActivity = idActivity;
        this.name = name;
        this.durationTime = durationTime;
        this.implementacion = implementacion;
    }

    // Getters y setters
    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getImplementacion() {
        return implementacion;
    }

    public void setImplementacion(String implementacion) {
        this.implementacion = implementacion;
    }
}
