package com.example.poyectofinalparalela.visuales;

import com.example.poyectofinalparalela.transito.Intersection;
import com.example.poyectofinalparalela.transito.Vehicle;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

public class ControladorVista {

    @FXML
    private Pane pane;

    @FXML
    private Rectangle interseccion;

    private Map<String, Rectangle> vehiculos = new HashMap<>();

    public void initialize(List<Intersection> calles) {
        for (Intersection calle : calles) {
            PriorityBlockingQueue<Vehicle> vehiculos = calle.getVehicles();
            for (Vehicle vehiculo : vehiculos) {
                Rectangle rectangle = createVehicleRectangle(vehiculo.getDirection());
                // Ajustar la posición del rectángulo según la dirección y la calle
                switch (vehiculo.getDirection()) {
                    case "N":
                        rectangle.setX(689); // Ajustar posición X e Y según la dirección y calle
                        rectangle.setY(39);
                        break;
                    case "S":
                        rectangle.setX(743);
                        rectangle.setY(664);
                        break;
                    case "E":
                        rectangle.setX(1227);
                        rectangle.setY(316);
                        break;
                    case "W":
                        rectangle.setX(114);
                        rectangle.setY(384);
                        break;
                }
                pane.getChildren().add(rectangle); // Agregar rectángulo al panel
            }
        }
    }

    // Método para crear un rectángulo visual de vehículo
    private Rectangle createVehicleRectangle(String direction) {
        Rectangle vehiculo = new Rectangle(30, 20, Color.BLUE); // Dimensiones y color de ejemplo
        // Podrías ajustar el color según la dirección u otra lógica necesaria
        return vehiculo;
    }
//@FXML
//public void initialize() {
//    // Initialize vehicles if needed. This is just an example.
//    vehiculos.put("1", createVehicle(Color.BLUE, 686, 39));
//    vehiculos.put("2", createVehicle(Color.GREEN, 686, 39));
//    vehiculos.put("3", createVehicle(Color.YELLOW, 686, 39));
//    //este
//    vehiculos.put("4", createVehicle(Color.ORANGE, 1227, 316));
//    vehiculos.put("5", createVehicle(Color.ORANGE, 1227, 316));
//    //sur
//    vehiculos.put("6", createVehicle(Color.ORANGE, 743, 664));
//    vehiculos.put("7", createVehicle(Color.ORANGE, 743, 664));
//    //oeste
//    vehiculos.put("8", createVehicle(Color.ORANGE, 114, 384 ));
//    pane.getChildren().addAll(vehiculos.values());
//}
//
//    private Rectangle createVehicle(Color color, double x, double y) {
//        Rectangle vehiculo = new Rectangle(30, 20, color);
//        vehiculo.setX(x);
//        vehiculo.setY(y);
//        return vehiculo;
//    }

    public void moverVehiculoInterseccion(String vehicleId, String direction) {
        Platform.runLater(() -> {
            Rectangle vehiculo = vehiculos.get(vehicleId);
            if (vehiculo == null) {
                vehiculo = new Rectangle(30, 20, Color.BLUE);
                vehiculos.put(vehicleId, vehiculo);
                pane.getChildren().add(vehiculo);

                switch (direction) {
                    case "N":
                        vehiculo.setX(700);
                        vehiculo.setY(700);
                        break;
                    case "S":
                        vehiculo.setX(700);
                        vehiculo.setY(0);
                        break;
                    case "E":
                        vehiculo.setX(0);
                        vehiculo.setY(350);
                        break;
                    case "W":
                        vehiculo.setX(1420);
                        vehiculo.setY(350);
                        break;
                }
            }

            double stopX = vehiculo.getX();
            double stopY = vehiculo.getY();

            switch (direction) {
                case "N":
                    stopY = interseccion.getY() + interseccion.getHeight();
                    break;
                case "S":
                    stopY = interseccion.getY() - vehiculo.getHeight();
                    break;
                case "E":
                    stopX = interseccion.getX() - vehiculo.getWidth();
                    break;
                case "W":
                    stopX = interseccion.getX() + interseccion.getWidth();
                    break;
            }

            TranslateTransition transition = new TranslateTransition(Duration.seconds(5), vehiculo);
            transition.setToX(stopX - vehiculo.getX());
            transition.setToY(stopY - vehiculo.getY());

            transition.setOnFinished(event -> {
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(pauseEvent -> cruzarInterseccion(vehicleId, direction));
                pause.play();
            });

            transition.play();
            System.out.println("Vehicle " + vehicleId + " is moving to the intersection.");
        });
    }

    public void cruzarInterseccion(String vehicleId, String direction) {
        Platform.runLater(() -> {
            Rectangle vehiculo = vehiculos.get(vehicleId);
            if (vehiculo == null) return;

            TranslateTransition crossTransition = new TranslateTransition(Duration.seconds(5), vehiculo);

            switch (direction) {
                case "N":
                    crossTransition.setByY(-interseccion.getY() - vehiculo.getHeight() * 100);
                    break;
                case "S":
                    crossTransition.setByY(interseccion.getY() + interseccion.getHeight() * 100);
                    break;
                case "E":
                    crossTransition.setByX(interseccion.getX() + interseccion.getWidth() * 100);
                    break;
                case "W":
                    crossTransition.setByX(-interseccion.getX() - vehiculo.getWidth() * 100);
                    break;
            }

            crossTransition.play();
            System.out.println("Vehicle " + vehicleId + " is crossing the intersection.");
        });
    }
}
