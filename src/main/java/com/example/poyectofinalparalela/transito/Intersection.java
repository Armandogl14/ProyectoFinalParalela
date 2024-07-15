package com.example.poyectofinalparalela.transito;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Intersection {
    private String id;
    private ConcurrentLinkedQueue<Vehicle> vehicles;
    private ConcurrentLinkedQueue<Vehicle> crossingVehicles;
    private boolean isIntersectionFree;

    public Intersection(String id, boolean isIntersectionFree) {
        this.id = id;
        this.vehicles = new ConcurrentLinkedQueue<>();
        this.crossingVehicles = new ConcurrentLinkedQueue<>();
        this.isIntersectionFree = isIntersectionFree;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConcurrentLinkedQueue<Vehicle> getVehicles() {
        return  vehicles;
    }

    public void setVehicles(ConcurrentLinkedQueue<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle getNextVehicle() {
        return vehicles.poll();
    }

    public ConcurrentLinkedQueue<Vehicle> getCrossingVehicles() {
        return crossingVehicles;
    }

    public void setCrossingVehicles(ConcurrentLinkedQueue<Vehicle> crossingVehicles) {
        this.crossingVehicles = crossingVehicles;
    }

    public boolean isIntersectionFree() {
        return isIntersectionFree;
    }

    public void setIntersectionFree(boolean intersectionFree) {
        isIntersectionFree = intersectionFree;
    }

    //Este no creo que sea necesario, ya que el condicional se llama cuando se agrega un vehiculo para verificar si es emergencia o no.
    /*public boolean hasEmergencyVehicle() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isEmergency()) {
                return true;
            }
        }
        return false;
    }*/
    public List<Vehicle> getVehiclesList() {
        return List.copyOf(vehicles);
    }

    public void handleEmergency(String direction){
        for(Vehicle vehicle : vehicles){
            if(vehicle.getDirection().equalsIgnoreCase(direction)){
                //Aqui va el metodo de cruzar, creo que va en las visuales porque tiene animaciones
                if(vehicle.isEmergency()){
                    break;
                }
            }
        }
    }

    //La idea de este es que cuando in vehiculo llegue a la interseccion, se llame a este metodo.
    public void crossIntersection(Vehicle vehicle){
        if(isIntersectionFree){
            isIntersectionFree = false;
            //Aqui van las animaciones
            isIntersectionFree = true;
        }
    }
}