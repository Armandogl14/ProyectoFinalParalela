package com.example.poyectofinalparalela.transito;

import com.example.poyectofinalparalela.visuales.ControladorVista;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Intersection {
    private String id; 
    private ConcurrentLinkedQueue<Vehicle> vehicles;
    private volatile boolean isIntersectionFree;
    public static int vID = 0;
    ControladorVista controladorVista = new ControladorVista();

    public Intersection(String id, boolean isIntersectionFree, ControladorVista controladorVista) {
        this.id = id;
        this.vehicles = new ConcurrentLinkedQueue<>();
        this.isIntersectionFree = isIntersectionFree;
        this.controladorVista = controladorVista;
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
                controladorVista.crussingVisual(vehicle);
                if(vehicle.isEmergency()){
                    break;
                }
            }
        }
    }
}