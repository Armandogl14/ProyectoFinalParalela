package com.example.poyectofinalparalela.transito;

import java.util.concurrent.PriorityBlockingQueue;

public class Street {
    private String id;
    private PriorityBlockingQueue<Vehicle> vehicles;
    private TrafficLight trafficLight; //Semaforo que permite que los autos de dicha calle crucen la intersección

    public Street(String id, TrafficLight trafficLight) {
        this.id = id;
        this.vehicles = new PriorityBlockingQueue<>(10);
        this.trafficLight = trafficLight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PriorityBlockingQueue<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(PriorityBlockingQueue<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle getNextVehicle(String direction) {
        return vehicles.poll();
    }

    public boolean hasEmergencyVehicle() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isEmergency()) {
                return true;
            }
        }
        return false;
    }
}
