package com.example.poyectofinalparalela;

import com.example.poyectofinalparalela.transito.Intersection;
import com.example.poyectofinalparalela.transito.TrafficController;
import com.example.poyectofinalparalela.transito.TrafficLight;
import com.example.poyectofinalparalela.transito.Vehicle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Phaser;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        /*
        // Crear semáforos y asignar el mismo Phaser
        Phaser phaser = new Phaser();

        TrafficLight northLight = new TrafficLight("NORTH", phaser);
        TrafficLight southLight = new TrafficLight("SOUTH", phaser);
        TrafficLight eastLight = new TrafficLight("EAST", phaser);
        TrafficLight westLight = new TrafficLight("WEST", phaser);

        // Agregar semáforos a la lista
        List<TrafficLight> trafficLights = new ArrayList<>();
        trafficLights.add(northLight);
        trafficLights.add(southLight);
        trafficLights.add(eastLight);
        trafficLights.add(westLight);

        // Crear la intersección
        Intersection intersection = new Intersection("MainIntersection");

        // Crear el controlador de tráfico
        TrafficController trafficController = new TrafficController(intersection, trafficLights);

        // Iniciar el control del tráfico
        trafficController.startControl();

        // Agregar vehículos periódicamente en diferentes direcciones
        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    String direction = switch (i % 4) {
                        case 0 -> "NORTH";
                        case 1 -> "SOUTH";
                        case 2 -> "EAST";
                        case 3 -> "WEST";
                        default -> "NORTH";
                    };
                    String type = (i % 10 == 0) ? "emergency" : "car"; // Cada 10º vehículo es de emergencia
                    Vehicle vehicle = new Vehicle("V" + i, type, direction);
                    intersection.addVehicle(vehicle);
                    Thread.sleep(500); // Agrega un vehículo cada 0.5 segundos
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Esperar a que el usuario presione una tecla para cerrar el programa
        System.out.println("Presione Enter para detener el programa...");
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
        }

        // Detener el control del tráfico
        trafficController.stopControl();
        System.out.println("Programa detenido.");

        System.exit(0);*/
    }
}