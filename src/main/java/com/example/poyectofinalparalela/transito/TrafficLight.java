package com.example.poyectofinalparalela.transito;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.Phaser;

public class TrafficLight {
    private String id;
    private AtomicBoolean green;
    private Phaser phaser;

    public TrafficLight(String id, Phaser phaser) {
        this.id = id;
        this.green = new AtomicBoolean(false);
        this.phaser = phaser;
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

    public void changeLight() {
        green.set(!green.get());
        phaser.arriveAndAwaitAdvance(); // Synchronize the change with other traffic lights
    }

    public void setPhaser(Phaser phaser) {
        this.phaser = phaser;
    }
}
