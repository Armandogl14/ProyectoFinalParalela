package com.example.poyectofinalparalela.transito;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Intersection {
    private String id;
    private PriorityBlockingQueue<Vehicle> vehicles;
    private boolean isIntersectionFree;
    public static int vID = 0;

    public Intersection(String id, boolean isIntersectionFree) {
        this.id = id;
        this.vehicles = new PriorityBlockingQueue<>();
        this.isIntersectionFree = isIntersectionFree;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PriorityBlockingQueue<Vehicle> getVehicles() {
        return  vehicles;
    }

    public void setVehicles(PriorityBlockingQueue<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vID++;
    }

    public Vehicle getNextVehicle() {
        return vehicles.poll();
    }

    public boolean isIntersectionFree() {
        return isIntersectionFree;
    }

    public void setIntersectionFree(boolean intersectionFree) {
        isIntersectionFree = intersectionFree;
    }

    public static int getvID() {
        return vID;
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

    public void handleEmergency(String origen){
        for(Vehicle vehicle : vehicles){
            if(vehicle.getOrigin().equalsIgnoreCase(origen)){
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