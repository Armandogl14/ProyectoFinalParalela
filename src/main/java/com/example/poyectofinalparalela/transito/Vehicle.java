package com.example.poyectofinalparalela.transito;

import javafx.scene.image.Image;

public class Vehicle implements Comparable<Vehicle> {
    String id;
    private boolean isEmergency;
    private String direction;
    private boolean inIntersection;
    private int X;
    private int Y;
    private int sizeY;
    private int sizeX;
    private Image image;

    public Vehicle(String id, boolean isEmergency, String direction) {
        this.id = id;
        this.isEmergency = isEmergency;
        this.direction = direction;
        this.inIntersection = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEmergency() {
        return isEmergency;
    }

    public void setEmergency(boolean isEmergency) {
        this.isEmergency = isEmergency;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isInIntersection() {
        return inIntersection;
    }

    public void setInIntersection(boolean inIntersection) {
        this.inIntersection = inIntersection;
    }

    @Override
    public int compareTo(Vehicle other) {
        return this.id.compareTo(other.id);
    }
}
