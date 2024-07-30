package com.example.poyectofinalparalela.transito;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.Phaser;

public class TrafficLight {
    private String id;
    private AtomicBoolean green;

    public TrafficLight(String id) {
        this.id = id;
        this.green = new AtomicBoolean(false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGreen() {
        return green.get();
    }

    public void setGreen(boolean green) {
        this.green.set(green);
    }

    public void changeLight() {
        green.set(!green.get());
    }
}
