package com.example.poyectofinalparalela.transito;

import javafx.scene.image.Image;

import java.util.Date;

public class Vehicle implements Comparable<Vehicle> {
    String id;
    private boolean isEmergency;
    private String direction;
    private String origin;
    private boolean inIntersection;
    private int X;
    private int Y;
    private int sizeY;
    private int sizeX;
    private Image image;
    private Date creacion;

    public Vehicle(String id, boolean isEmergency, String direction, String origin, int X, int Y, int sizeX, int sizeY, Image image) {
        this.id = id;
        this.isEmergency = isEmergency;
        this.direction = direction;
        this.origin = origin;
        this.inIntersection = false;
        this.X = X;
        this.Y = Y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.image = image;
        this.creacion = new Date();
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    @Override
    public int compareTo(Vehicle other) {
        return this.creacion.compareTo(other.creacion);
    }
}
