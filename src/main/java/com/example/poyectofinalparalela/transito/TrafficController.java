package com.example.poyectofinalparalela.transito;

import com.example.poyectofinalparalela.visuales.ControladorVista;
import javafx.application.Platform;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficController {
    private List<Street> streets;
    private List<TrafficLight> trafficLights;
    private ScheduledExecutorService executor;
    ExecutorService exec;
    ReentrantLock lock;
    private volatile boolean intersectionOccupied = false;
    private ControladorVista controladorVista;

    public TrafficController(List<TrafficLight> trafficLights, List<Street> streets, ControladorVista controladorVista) {
        this.streets = streets;
        this.trafficLights = trafficLights;
        this.executor = Executors.newScheduledThreadPool(1);
        this.exec = Executors.newFixedThreadPool(streets.size());
        this.lock = new ReentrantLock();
        this.controladorVista = controladorVista;
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
            } else {
                vehicle = street.getNextVehicle();
                if (vehicle != null) {
                    tryToCrossIntersection(vehicle);
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
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
                break;
            }
        }
    }

    private void tryToCrossIntersection(Vehicle vehicle) {
        try {
            lock.lock();
            while (intersectionOccupied) {
                controladorVista.moverVehiculoInterseccion(vehicle.getId(), vehicle.getDirection());
                System.out.println("Vehicle " + vehicle.getId() + " is waiting to cross the intersection");
                lock.unlock();
                Thread.sleep(10000);
                lock.lock();
            }
            intersectionOccupied = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

        System.out.println("Vehicle " + vehicle.getId() + " is stopping at the stop sign");
        try {
            Thread.sleep(500);
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
        controladorVista.cruzarInterseccion(vehicle.getId(), vehicle.getDirection());
        try {
            Thread.sleep(5000);
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
