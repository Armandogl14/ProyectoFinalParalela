package com.example.poyectofinalparalela.transito;

import com.example.poyectofinalparalela.visuales.ControladorEscenario2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Interseccion_caso2 {
    private static int vID = 0;
    private String id;
    private volatile boolean isIntersectionFree;
    private ControladorEscenario2 controladorVista;
    private Lock lock;
    private Condition intersectionFreeCondition;
    private List<Vehicle> vehicles;

    public Interseccion_caso2(String id, boolean isIntersectionFree, ControladorEscenario2 controladorVista) {
        this.id = id;
        this.isIntersectionFree = isIntersectionFree;
        this.controladorVista = controladorVista;
        this.lock = new ReentrantLock();
        this.intersectionFreeCondition = lock.newCondition();
        this.vehicles = new ArrayList<>();
    }

    public static int getvID() {
        return vID++;
    }

    public boolean isIntersectionFree() {
        return isIntersectionFree;
    }

    public void setIntersectionFree(boolean isIntersectionFree) {
        this.isIntersectionFree = isIntersectionFree;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        controladorVista.addVehicle(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void handleEmergency(Vehicle vehicle) {
        lock.lock();
        try {
            while (!isIntersectionFree) {
                System.out.println("Waiting for intersection to be free");
                intersectionFreeCondition.await();
            }
            isIntersectionFree = false;
            System.out.println("Intersection is free");
            controladorVista.crussingVisual(vehicle);
            vehicles.remove(vehicle);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            isIntersectionFree = true;
            intersectionFreeCondition.signalAll();
            lock.unlock();
        }
    }

    public void handleNormal(Vehicle vehicle) {
        lock.lock();
        try {
            while (!isIntersectionFree) {
                System.out.println("Waiting for intersection to be free");
                intersectionFreeCondition.await();
            }
            isIntersectionFree = false;
            System.out.println("Intersection is free");
            controladorVista.crussingVisual(vehicle);
            vehicles.remove(vehicle);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            isIntersectionFree = true;
            intersectionFreeCondition.signalAll();
            lock.unlock();
        }
    }





}
