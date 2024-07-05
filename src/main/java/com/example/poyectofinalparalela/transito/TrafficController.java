package com.example.poyectofinalparalela.transito;

import java.util.List;
import java.util.concurrent.*;

public class TrafficController {
    private Intersection intersection;
    private List<TrafficLight> trafficLights;
    private ScheduledExecutorService executor;
    private Phaser phaser;

    public TrafficController(Intersection intersection, List<TrafficLight> trafficLights) {
        this.intersection = intersection;
        this.trafficLights = trafficLights;
        this.phaser = new Phaser(trafficLights.size() + 1);
        this.executor = Executors.newScheduledThreadPool(trafficLights.size() + 2);
    }

    public void startControl() {
        for (TrafficLight trafficLight : trafficLights) {
            trafficLight.setPhaser(phaser);
            executor.scheduleAtFixedRate(trafficLight::changeLight, 0, 60, TimeUnit.SECONDS);
        }
        executor.scheduleAtFixedRate(this::manageIntersection, 0, 1, TimeUnit.SECONDS);
    }

    public void manageIntersection() {
        phaser.arriveAndAwaitAdvance();
        synchronized (this) {
            for (TrafficLight trafficLight : trafficLights) {
                if (trafficLight.isGreen()) {
                    String direction = trafficLight.getId();
                    while (intersection.hasEmergencyVehicle(direction)) {
                        Vehicle emergencyVehicle = intersection.getNextVehicle(direction);
                        if (emergencyVehicle != null) {
                            processVehicleCrossing(emergencyVehicle, direction);
                        }
                    }
                    Vehicle nextVehicle = intersection.getNextVehicle(direction);
                    if (nextVehicle != null) {
                        processVehicleCrossing(nextVehicle, direction);
                    }
                }
            }
        }
    }

    //Este metodo seguramente habra que cambiarlo, ya que solamente imprime en consola, no hace mas nada
    private void processVehicleCrossing(Vehicle vehicle, String direction) {
        System.out.println("El vehículo " + vehicle.getId() + " está cruzando desde " + direction);
        executor.schedule(() -> System.out.println("El vehículo " + vehicle.getId() + " ha cruzado desde " + direction), 5, TimeUnit.SECONDS);
    }

    public void stopControl() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
