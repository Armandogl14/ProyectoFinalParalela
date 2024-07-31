package com.example.poyectofinalparalela.visuales;

import com.example.poyectofinalparalela.transito.Intersection;
import com.example.poyectofinalparalela.transito.Vehicle;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.*;

public class ControladorVista {
    @FXML
    private Rectangle Referenciia_oeste;

    @FXML
    private Rectangle Referenciia_sur;

    @FXML
    private Rectangle Referenciia_este;

    @FXML
    private Rectangle Referenciia_norte;

    @FXML
    private Rectangle Llegada_oeste;

    @FXML
    private Rectangle Llegada_este;

    @FXML
    private Pane pane;

    @FXML
    private Rectangle interseccion;

    private Map<String, Rectangle> vehiculos = new HashMap<>();
    private ConcurrentLinkedQueue<Vehicle> vehicles = new ConcurrentLinkedQueue<>();
    private List<Vehicle> vehicleList = new LinkedList<>();
    private Intersection intersection;
    private ExecutorService executor;

    public void initialize() {
        this.intersection = new Intersection("I1", true, this);
        this.executor = Executors.newCachedThreadPool();
    }

    @FXML
    private void handleBtnNormalNorte() {
        System.out.println("Botón normal norte presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), false, direccion, "N", 666, 0, 30, 20, null);
        intersection.addVehicle(carro);
        executor.submit(() -> handleNormalIntersection(carro));
    }

    @FXML
    private void handleBtnNorteEmergencia() {
        System.out.println("Botón norte emergencia presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), true, direccion, "N", 666, 0, 30, 20, null);
        intersection.addVehicle(carro);
        executor.submit(() -> handleEmergencyIntersection(carro));
    }

    @FXML
    private void handleBtnNormalSur() {
        System.out.println("Botón normal sur presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), false, direccion, "S", 838, 664, 30, 20, null);
        intersection.addVehicle(carro);
        executor.submit(() -> handleNormalIntersection(carro));
    }

    @FXML
    private void handleBtnSurEmergencia() {
        System.out.println("Botón sur emergencia presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), true, direccion, "S", 838, 664, 30, 20, null);
        intersection.addVehicle(carro);
        executor.submit(() -> handleEmergencyIntersection(carro));
    }

    @FXML
    private void handleBtnNormalEste() {
        System.out.println("Botón normal este presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), false, direccion, "E", 1414, 305, 30, 20, null);
        intersection.addVehicle(carro);
        executor.submit(() -> handleNormalIntersection(carro));
    }

    @FXML
    private void handleBtnEsteEmergencia() {
        System.out.println("Botón este emergencia presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), true, direccion, "E", 1414, 305, 30, 20, null);
        intersection.addVehicle(carro);
        executor.submit(() -> handleEmergencyIntersection(carro));
    }

    @FXML
    private void handleBtnNormalOeste() {
        System.out.println("Botón normal oeste presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), false, direccion, "W", 0, 402, 30, 20, null);
        intersection.addVehicle(carro);
        System.out.println("Vehicle " + carro.getId() + " added to the queue.");
        executor.submit(() -> handleNormalIntersection(carro));
    }

    @FXML
    private void handleBtnOesteEmergencia() {
        System.out.println("Botón oeste emergencia presionado.");
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), true, direccion, "W", 0, 402, 30, 20, null);
        intersection.addVehicle(carro);
        executor.submit(() -> handleEmergencyIntersection(carro));
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle " + vehicle.getId() + " added to the queue.");
        Platform.runLater(this::addVehicleVisual);
        vehicleList.add(vehicle);
    }

    public String getDireccion() {
        String[] directions = {"Izquierda", "Derecha", "Recto", "U-Turn"};
        String direccion = directions[new Random().nextInt(directions.length)];
        return direccion != null ? direccion : "Recto";
    }

    public void addVehicleVisual() {
        if (!vehicleList.isEmpty()) {
            Vehicle vehicle = vehicleList.get(vehicleList.size() - 1);
            Rectangle vehiculo = null;
            if (vehicle != null) {
                if (vehicle.isEmergency()) {
                    vehiculo = new Rectangle(vehicle.getSizeX(), vehicle.getSizeY(), Color.BLUE);
                    vehiculo.setStroke(Color.RED);
                    vehiculo.setStrokeWidth(2);
                } else {
                    vehiculo = new Rectangle(vehicle.getSizeX(), vehicle.getSizeY(), Color.RED);
                }

                double startX = 0;
                double startY = 0;
                switch (vehicle.getOrigin()) {
                    case "N":
                        startX = Referenciia_norte.getLayoutX();
                        startY = Referenciia_norte.getLayoutY();
                        vehicle.setX((int) startX);
                        vehicle.setY((int) startY);
                        break;
                    case "S":
                        startX = Referenciia_sur.getLayoutX();
                        startY = Referenciia_sur.getLayoutY();
                        vehicle.setX((int) startX);
                        vehicle.setY((int) startY);
                        break;
                    case "E":
                        startX = Referenciia_este.getLayoutX();
                        startY = Referenciia_este.getLayoutY();
                        vehicle.setX((int) startX);
                        vehicle.setY((int) startY);
                        break;
                    case "W":
                        startX = Referenciia_oeste.getLayoutX();
                        startY = Referenciia_oeste.getLayoutY();
                        vehicle.setX((int) startX);
                        vehicle.setY((int) startY);
                        break;
                }

                vehiculo.setLayoutX(startX);
                vehiculo.setLayoutY(startY);

                vehiculos.put(vehicle.getId(), vehiculo);
                pane.getChildren().add(vehiculo);

                TranslateTransition transition = new TranslateTransition();
                transition.setNode(vehiculo);
                transition.setDuration(Duration.seconds(5));

                double endX = 0;
                double endY = 0;
                switch (vehicle.getOrigin()) {
                    case "N":
                        endY = interseccion.getLayoutY() - startY - vehicle.getSizeY();
                        vehicle.setY((int) endY);
                        break;
                    case "S":
                        endY = interseccion.getLayoutY() + interseccion.getHeight() - startY;
                        vehicle.setY((int) endY);
                        break;
                    case "E":
                        endX = interseccion.getLayoutX() + interseccion.getWidth() - startX;
                        vehicle.setX((int) endX);
                        break;
                    case "W":
                        endX = interseccion.getLayoutX() - startX - vehicle.getSizeX();
                        vehicle.setX((int) endX);
                        break;
                }
                transition.setByX(endX);
                transition.setByY(endY);

                transition.play();

                transition.setOnFinished(event -> {
                    while (!intersection.isIntersectionFree()) {}
                    intersection.setIntersectionFree(false);
                    Platform.runLater(() -> crussingVisual(vehicle));
                    intersection.setIntersectionFree(true);
                    intersection.getVehicles().remove(vehicle);
                    vehicles.remove(vehicle);
                });
            }
        }
    }

    public void crussingVisual(Vehicle vehicle) {
        Rectangle vehiculo = vehiculos.get(vehicle.getId());

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(vehiculo);
        transition.setDuration(Duration.seconds(1));

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(vehiculo);
        transition2.setDuration(Duration.seconds(2));

        double endX = 0;
        double endY = 0;
        switch (vehicle.getOrigin()) {
            case "N":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        endY = Referenciia_sur.getLayoutY() - vehiculo.getLayoutY();
                        transition.setByY(endY);
                        vehicle.setY((int) endY);
                        break;
                    case "Derecha":
                        endY = Referenciia_oeste.getLayoutY() / 4;
                        transition.setByY(endY);
                        endX = Referenciia_oeste.getLayoutX() - vehiculo.getLayoutX();
                        transition2.setByX(endX);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "Izquierda":
                        endY = Referenciia_este.getLayoutY() / 2;
                        transition.setByY(endY);
                        endX = Referenciia_este.getLayoutX() - vehiculo.getLayoutX();
                        transition2.setByX(endX);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "U-Turn":
                        endY =  interseccion.getHeight() * 2;
                        transition.setByY(endY);
                        endY = Referenciia_norte.getLayoutY() - vehiculo.getLayoutY();
                        transition2.setByY(endY);
                        vehicle.setY((int) endY);
                        break;
                }
                break;
            case "S":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        endY = Referenciia_norte.getLayoutY() - vehiculo.getLayoutY();
                        transition.setByY(endY);
                        vehicle.setY((int) endY);
                        break;
                    case "Derecha":
                        endY = -Llegada_este.getLayoutY() / 4;
                        transition.setByY(endY);
                        endX =Referenciia_este.getLayoutX() - vehiculo.getLayoutX();
                        transition2.setByX(endX);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "Izquierda":
                        endY = Referenciia_oeste.getLayoutX() - vehiculo.getLayoutX();
                        transition.setByY(endY);
                        endX = -Llegada_oeste.getLayoutY() / 2;
                        transition2.setByX(endX);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "U-Turn":
                        endY = -Referenciia_sur.getHeight() * 2;
                        transition.setByY(endY);
                        endY = Referenciia_sur.getLayoutY() - vehiculo.getLayoutY();
                        transition2.setByY(endY);
                        vehicle.setY((int) endY);
                        break;
                }
                break;
            case "E":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        endX = Referenciia_oeste.getLayoutX() - vehiculo.getLayoutX() - vehiculo.getWidth();
                        transition.setByX(endX);
                        vehicle.setX((int) endX);
                        break;
                    case "Derecha":
                        endX = Referenciia_sur.getLayoutY() - vehiculo.getLayoutY();
                        transition.setByX(endX);
                        endY =- Referenciia_sur.getLayoutX() / 3;
                        transition2.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "Izquierda":
                        endX = -Referenciia_norte.getLayoutX() /4;
                        transition.setByX(endX);
                        endY = Referenciia_norte.getLayoutY() - vehiculo.getLayoutY();
                        transition2.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "U-Turn":
                        endX = interseccion.getLayoutX() + interseccion.getWidth() - vehiculo.getLayoutX();
                        transition.setByX(endX);
                        endX = Referenciia_este.getLayoutX() - vehiculo.getLayoutX() - vehiculo.getWidth();
                        transition2.setByX(endX);
                        vehicle.setX((int) endX);
                        break;
                }
                break;
            case "W":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        endX = Referenciia_este.getLayoutX() + Referenciia_este.getWidth() - vehiculo.getLayoutX();
                        transition.setByX(endX);
                        vehicle.setX((int) endX);
                        break;
                    case "Derecha":
                        endX =Referenciia_norte.getLayoutX() / 2;
                        transition.setByX(endX);
                        endY = Referenciia_norte.getLayoutY() - vehiculo.getLayoutY();
                        transition2.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "Izquierda":
                        endX = Referenciia_sur.getLayoutX() / 5;
                        transition.setByX(endX);
                        endY =Referenciia_sur.getLayoutY() - vehiculo.getLayoutY();
                        transition2.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        break;
                    case "U-Turn":
                        endX = interseccion.getLayoutX() - vehiculo.getLayoutX() - vehiculo.getWidth();
                        transition.setByX(endX);
                        endX = Referenciia_oeste.getLayoutX() + Referenciia_oeste.getWidth() - vehiculo.getLayoutX();
                        transition2.setByX(endX);
                        vehicle.setX((int) endX);
                        break;
                }
                break;
        }

        SequentialTransition sequentialTransition = new SequentialTransition(transition, transition2);
        sequentialTransition.play();

        sequentialTransition.setOnFinished(event -> {
            pane.getChildren().remove(vehiculo);
        });
    }

    public Vehicle getLastVehicle() {
        return vehicles.isEmpty() ? null : vehicles.peek();
    }

    private void handleNormalIntersection(Vehicle vehicle) {
        try {
            intersection.handleNormal(vehicle);
            Platform.runLater(() -> addVehicleVisual());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEmergencyIntersection(Vehicle emergencyVehicle) {
        try {
            intersection.handleEmergency(emergencyVehicle.getOrigin());
            Platform.runLater(() -> addVehicleVisual());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
