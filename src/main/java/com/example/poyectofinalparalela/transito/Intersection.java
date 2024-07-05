package com.example.poyectofinalparalela.transito;

import java.util.concurrent.PriorityBlockingQueue;

public class Intersection {
    private String id;
    private PriorityBlockingQueue<Vehicle> northQueue;
    private PriorityBlockingQueue<Vehicle> southQueue;
    private PriorityBlockingQueue<Vehicle> eastQueue;
    private PriorityBlockingQueue<Vehicle> westQueue;

    public Intersection(String id) {
        this.id = id;
        this.northQueue = new PriorityBlockingQueue<>();
        this.southQueue = new PriorityBlockingQueue<>();
        this.eastQueue = new PriorityBlockingQueue<>();
        this.westQueue = new PriorityBlockingQueue<>();
    }

    public void addVehicle(Vehicle vehicle) {
        switch (vehicle.getDirection()) {
            case "NORTH":
                northQueue.add(vehicle);
                break;
            case "SOUTH":
                southQueue.add(vehicle);
                break;
            case "EAST":
                eastQueue.add(vehicle);
                break;
            case "WEST":
                westQueue.add(vehicle);
                break;
        }
    }

    public Vehicle getNextVehicle(String direction) {
        switch (direction) {
            case "NORTH":
                return northQueue.poll();
            case "SOUTH":
                return southQueue.poll();
            case "EAST":
                return eastQueue.poll();
            case "WEST":
                return westQueue.poll();
            default:
                return null;
        }
    }

    public boolean hasEmergencyVehicle(String direction) {
        return switch (direction) {
            case "NORTH" -> northQueue.peek() != null && northQueue.peek().isEmergency();
            case "SOUTH" -> southQueue.peek() != null && southQueue.peek().isEmergency();
            case "EAST" -> eastQueue.peek() != null && eastQueue.peek().isEmergency();
            case "WEST" -> westQueue.peek() != null && westQueue.peek().isEmergency();
            default -> false;
        };
    }
}
