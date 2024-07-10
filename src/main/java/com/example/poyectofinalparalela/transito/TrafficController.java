package com.example.poyectofinalparalela.transito;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficController {
    private List<Street> streets; //Lista de calles (Intersection representa una calle)
    private List<TrafficLight> trafficLights;
    private ScheduledExecutorService executor;
    ExecutorService exec;
    ReentrantLock lock;
    //private Phaser phaser;
    private volatile boolean intersectionOccupied = false;

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
        while (true) {
            Vehicle vehicle;
            if (street.hasEmergencyVehicle()) {
                processEmergencyVehicle(street);
                return; // Una vez procesados todos los vehículos de emergencia, salir del bucle
            } else {
                vehicle = street.getNextVehicle();
                if (vehicle == null) {
                    break; // Si no hay más vehículos, salir del bucle
                }
                tryToCrossIntersection(vehicle);
            }
        }
    }

    private void processEmergencyVehicle(Street street) {
        System.out.println("Emergency vehicle detected on street " + street.getId());
        Vehicle vehicle;
        while ((vehicle = street.getNextVehicle()) != null) {
            System.out.println("Vehicle " + vehicle.getId() + " crossing before emergency vehicle");
            crossIntersection(vehicle);
            if (vehicle.isEmergency()) {
                System.out.println("Emergency vehicle " + vehicle.getId() + " has crossed the intersection");
                break; // Detener el procesamiento después del cruce del vehículo de emergencia
            }
        }
    }

    private void tryToCrossIntersection(Vehicle vehicle) {
        try {
            lock.lock();
            while (intersectionOccupied) {
                System.out.println("Vehicle " + vehicle.getId() + " is waiting to cross the intersection");
                lock.unlock();
                Thread.sleep(100); // Esperar un poco antes de intentar nuevamente
                lock.lock();
            }
            intersectionOccupied = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

        // Simular la detención en una señal de "Stop"
        System.out.println("Vehicle " + vehicle.getId() + " is stopping at the stop sign");
        try {
            Thread.sleep(500); // Simular la detención en la señal de "Stop" (500 ms)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        crossIntersection(vehicle);

        try {
            lock.lock();
            intersectionOccupied = false;
        } finally {
            lock.unlock();
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
