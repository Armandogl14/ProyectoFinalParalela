package com.example.poyectofinalparalela.transito;

import java.util.List;
import java.util.concurrent.*;

public class TrafficController {
    private List<Street> streets; //Lista de calles (Intersection representa una calle)
    private List<TrafficLight> trafficLights;
    private ScheduledExecutorService executor;
    private Phaser phaser;

    public TrafficController(Street intersection, List<TrafficLight> trafficLights, List<Street> streets) {
        this.streets= streets;
        this.trafficLights = trafficLights;
        this.executor = Executors.newScheduledThreadPool(trafficLights.size() + 2);
        this.phaser = new Phaser(1);
    }

    public void startControl() {
        for (TrafficLight trafficLight : trafficLights) {
            executor.scheduleAtFixedRate(() -> {
                phaser.arriveAndAwaitAdvance(); // Esperar hasta que se permita cambiar
                trafficLight.changeLight();
            }, 0, 60, TimeUnit.SECONDS);
        }
        executor.scheduleAtFixedRate(this::manageIntersection, 0, 1, TimeUnit.SECONDS);
    }

    public void manageIntersection() {
        for (TrafficLight trafficLight : trafficLights) {
            if (trafficLight.isGreen()) {
                for (Street street : streets) {
                    if (street.getTrafficLight().equals(trafficLight)) {
                        if (street.hasEmergencyVehicle()) {
                            phaser.bulkRegister(1); // Bloquear cambios de semáforo
                            processVehiclesInStreet(street);
                            phaser.arriveAndDeregister(); // Liberar bloqueo después de procesar
                        } else {
                            Vehicle vehicle = street.getNextVehicle(street.getId());
                            if (vehicle != null) {
                                vehicle.setInIntersection(true);
                                System.out.println("Vehicle " + vehicle.getId() + " is crossing the intersection");
                                try {
                                    Thread.sleep(1000); // Simular el cruce de la intersección
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                vehicle.setInIntersection(false);
                            }
                        }
                    }
                }
            }
        }
    }

    private void processVehiclesInStreet(Street street) {
        Vehicle vehicle;
        while ((vehicle = street.getNextVehicle(street.getId())) != null) {
            vehicle.setInIntersection(true);
            System.out.println("Vehicle " + vehicle.getId() + " is crossing the intersection");
            try {
                Thread.sleep(1000); // Simular el cruce de la intersección
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            vehicle.setInIntersection(false);
            if (vehicle.isEmergency()) {
                break; // Detener el procesamiento después del cruce del vehículo de emergencia
            }
        }
    }

    public void stopControl() {
        executor.shutdown();
    }
}
