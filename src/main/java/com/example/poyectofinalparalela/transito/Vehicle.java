package com.example.poyectofinalparalela.transito;

public class Vehicle implements Comparable<Vehicle> {
    String id;
    private boolean isEmergency;
    private String direction;
    private boolean inIntersection;

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
