package com.example.poyectofinalparalela.transito;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficController {
    private List<Street> streets; //Lista de calles (Intersection representa una calle)
    private List<TrafficLight> trafficLights;
    private ScheduledExecutorService executor;
    ExecutorService exec;
    ReentrantLock lock;
    //private Phaser phaser;
    private boolean intersectionOccupied = false;

    public TrafficController(List<TrafficLight> trafficLights, List<Street> streets) {
        this.streets= streets;
        this.trafficLights = trafficLights;
        this.executor = Executors.newScheduledThreadPool(1);
        this.exec = Executors.newFixedThreadPool(streets.size());
        this.lock = new ReentrantLock();
        //this.phaser = new Phaser(1);
    }

    public void startControl() {
        executor.scheduleAtFixedRate(this::manageIntersection, 0, 1, TimeUnit.SECONDS);
    }

    public void manageIntersection() {
        for (Street street : streets) {
            exec.submit(() -> processStreet(street));
        }
    }

    private void processStreet(Street street) {
        Vehicle vehicle = street.getNextVehicle(street.getId());

        if (street.hasEmergencyVehicle()) {
            processEmergencyVehicle(street);
        }
        else{
            if (vehicle == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return;
            }

            tryToCrossIntersection(vehicle);
        }
    }

    private void processEmergencyVehicle(Street street) {
        Vehicle vehicle;
        while ((vehicle = street.getNextVehicle(street.getId())) != null) {
            crossIntersection(vehicle);
            if (vehicle.isEmergency()) {
                break;
            }
        }
    }

    private void tryToCrossIntersection(Vehicle vehicle) {

        if (lock.tryLock()) {
            try {
                // Simular la detención en una señal de "Stop"
                System.out.println("Vehicle " + vehicle.getId() + " is stopping at the stop sign");
                try {
                    Thread.sleep(500); // Simular la detención en la señal de "Stop" (500 ms)
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                if (!intersectionOccupied) {
                    intersectionOccupied = true;
                    crossIntersection(vehicle);
                    intersectionOccupied = false;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void crossIntersection(Vehicle vehicle) {
        vehicle.setInIntersection(true);
        System.out.println("Vehicle " + vehicle.getId() + " is crossing the intersection");
        try {
            Thread.sleep(1000); // Simular el cruce de la intersección
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        vehicle.setInIntersection(false);
    }

    public void stopControl() {
        executor.shutdown();
        exec.shutdown();
    }
}
