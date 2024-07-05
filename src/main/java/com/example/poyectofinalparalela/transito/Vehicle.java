package com.example.poyectofinalparalela.transito;

public class Vehicle implements Comparable<Vehicle> {
    String id;
    private String type;
    private String direction;
    private boolean inIntersection;

    public Vehicle(String id, String type, String direction) {
        this.id = id;
        this.type = type;
        this.direction = direction;
        this.inIntersection = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isEmergency() {
        return "emergency".equalsIgnoreCase(type);
    }

    @Override
    public int compareTo(Vehicle other) {
        if (this.isEmergency() && !other.isEmergency()) {
            return -1;
        } else if (!this.isEmergency() && other.isEmergency()) {
            return 1;
        }
        return this.id.compareTo(other.id);
    }
}
