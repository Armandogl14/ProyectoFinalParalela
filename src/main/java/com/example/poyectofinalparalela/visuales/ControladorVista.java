package com.example.poyectofinalparalela.visuales;

import com.example.poyectofinalparalela.transito.Intersection;
import com.example.poyectofinalparalela.transito.Vehicle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

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
    private Pane pane;

    @FXML
    private Rectangle interseccion;

    private Map<String, Rectangle> vehiculos = new HashMap<>();
    private PriorityBlockingQueue<Vehicle> vehicles = new PriorityBlockingQueue<>();
    private List<Vehicle> vehicleList = new LinkedList<>();
    private Vehicle vehicle;
    private Intersection intersection ;

    public void initialize() {
        //Aqui se llama el constructor que manejes los hilos
        this.intersection = new Intersection("I1", true);
    }
    @FXML
    private void handleBtnNormalNorte() {
        System.out.println("Botón normal norte presionado.");
        // Aquí va la lógica específica para este botón
        //tipo se crea un un vehiculo y se manda a la lista de vehiculos
        String[] directions = {"Izquierda", "Derecha", "Recto", "U-Turn"};

        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), false, null, "N", 666, 0, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        // X = 666, Y = 0
    }

    @FXML
    private void handleBtnNorteEmergencia() {
        System.out.println("Botón norte emergencia presionado.");
        // Aquí va la lógica específica para este botón
        //como crea un carro en logico y lo visual y hacer un cambio de estado para saber que hay un vehiculo de emergencia
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), true, getDireccion(), "N", 666, 0, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        intersection.handleEmergency("N");
        // X = 666, Y = 0
    }

    @FXML
    private void handleBtnNormalSur() {
        System.out.println("Botón normal sur presionado.");
        // Aquí va la lógica específica para este botón
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), false, getDireccion(), "S", 838, 664, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        // X = 838, Y = 664
    }

    @FXML
    private void handleBtnSurEmergencia() {
        System.out.println("Botón sur emergencia presionado.");
        // Aquí va la lógica específica para este botón
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), true, getDireccion(), "S", 838, 664, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        intersection.handleEmergency("S");
        // X = 838, Y = 664
    }

    @FXML
    private void handleBtnNormalEste() {
        System.out.println("Botón normal este presionado.");
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), false, getDireccion(), "E", 1414, 305, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        // Aquí va la lógica específica para este botón
        // X = 1414, Y = 305
    }

    @FXML
    private void handleBtnEsteEmergencia() {
        System.out.println("Botón este emergencia presionado.");
        // Aquí va la lógica específica para este botón
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), true, getDireccion(), "E", 1414, 305, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        intersection.handleEmergency("E");
        // X = 1414, Y = 305
    }

    @FXML
    private void handleBtnNormalOeste() {
        System.out.println("Botón normal oeste presionado.");
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), false, getDireccion(), "W", 0, 402, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        // Aquí va la lógica específica para este botón
        // X = 0, Y = 402
    }

    @FXML
    private void handleBtnOesteEmergencia() {
        System.out.println("Botón oeste emergencia presionado.");
        // Aquí va la lógica específica para este botón
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), true, getDireccion(), "W", 0, 402, 30, 20, null);
        addVehicle(carro);
        intersection.addVehicle(carro);
        intersection.handleEmergency("W");
        // X = 0, Y = 402
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle " + vehicle.getId() + " added to the queue.");
        addVehicleVisual();
        vehicleList.add(vehicle);
    }

    public String getDireccion() {
        String[] directions = {"Izquierda", "Derecha", "Recto", "U-Turn"};
        return directions[(int) (Math.random() * 4)];
    }
    //aqui haz los generadores de vehiculos

    public void addVehicleVisual() {
        if (!vehicleList.isEmpty()) {
            Vehicle vehicle = vehicleList.get(vehicleList.size() - 1); // Tomamos el último vehículo añadido
            if (vehicle != null) {
                // Crear el rectángulo que representa el vehículo
                Rectangle vehiculo = new Rectangle(vehicle.getSizeX(), vehicle.getSizeY(), Color.RED);

                // Determinar la posición inicial del vehículo basado en su dirección
                double startX = 0;
                double startY = 0;
                switch (vehicle.getOrigin()) {
                    case "N":
                        startX = Referenciia_norte.getLayoutX();
                        startY = Referenciia_norte.getLayoutY();
                        break;
                    case "S":
                        startX = Referenciia_sur.getLayoutX();
                        startY = Referenciia_sur.getLayoutY();
                        break;
                    case "E":
                        startX = Referenciia_este.getLayoutX();
                        startY = Referenciia_este.getLayoutY();
                        break;
                    case "W":
                        startX = Referenciia_oeste.getLayoutX();
                        startY = Referenciia_oeste.getLayoutY();
                        break;
                }

                vehiculo.setLayoutX(startX);
                vehiculo.setLayoutY(startY);

                // Añadir el rectángulo a la lista de vehículos y al pane
                vehiculos.put(vehicle.getId(), vehiculo);
                pane.getChildren().add(vehiculo);

                // Crear la transición para mover el vehículo hacia la intersección
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(vehiculo);
                transition.setDuration(Duration.seconds(2)); // Duración de la transición

                double endX = interseccion.getLayoutX() - startX;
                double endY = interseccion.getLayoutY() - startY;

                transition.setByX(endX);
                transition.setByY(endY);

                transition.play();

                // Verificar si el vehículo está en la intersección
                transition.setOnFinished(event -> {
                    if (isVehicleInIntersection(vehicle)) {
                        // Lógica específica si el vehículo está en la intersección
                        System.out.println("El vehículo está en la intersección.");
                    }
                });
            }
        }
    }

    private boolean isVehicleInIntersection(Vehicle vehicle) {
        double vehiculoX = vehicle.getX();
        double vehiculoY = vehicle.getY();
        double interseccionX = interseccion.getLayoutX();
        double interseccionY = interseccion.getLayoutY();
        double interseccionWidth = interseccion.getWidth();
        double interseccionHeight = interseccion.getHeight();

        return vehiculoX >= interseccionX && vehiculoX <= interseccionX + interseccionWidth &&
                vehiculoY >= interseccionY && vehiculoY <= interseccionY + interseccionHeight;
    }

//    public void moverVehiculoInterseccion(String vehicleId, String direction) {
//        Platform.runLater(() -> {
//            Rectangle vehiculo = vehiculos.get(vehicleId);
//            if (vehiculo == null) {
//                vehiculo = new Rectangle(30, 20, Color.BLUE);
//                vehiculos.put(vehicleId, vehiculo);
//                pane.getChildren().add(vehiculo);
//
//                switch (direction) {
//                    case "N":
//                        vehiculo.setX(700);
//                        vehiculo.setY(700);
//                        break;
//                    case "S":
//                        vehiculo.setX(700);
//                        vehiculo.setY(0);
//                        break;
//                    case "E":
//                        vehiculo.setX(0);
//                        vehiculo.setY(350);
//                        break;
//                    case "W":
//                        vehiculo.setX(1420);
//                        vehiculo.setY(350);
//                        break;
//                }
//            }
//
//            double stopX = vehiculo.getX();
//            double stopY = vehiculo.getY();
//
//            switch (direction) {
//                case "N":
//                    stopY = interseccion.getY() + interseccion.getHeight();
//                    break;
//                case "S":
//                    stopY = interseccion.getY() - vehiculo.getHeight();
//                    break;
//                case "E":
//                    stopX = interseccion.getX() - vehiculo.getWidth();
//                    break;
//                case "W":
//                    stopX = interseccion.getX() + interseccion.getWidth();
//                    break;
//            }
//
//            TranslateTransition transition = new TranslateTransition(Duration.seconds(5), vehiculo);
//            transition.setToX(stopX - vehiculo.getX());
//            transition.setToY(stopY - vehiculo.getY());
//
//            transition.setOnFinished(event -> {
//                PauseTransition pause = new PauseTransition(Duration.seconds(5));
//                pause.setOnFinished(pauseEvent -> cruzarInterseccion(vehicleId, direction));
//                pause.play();
//            });
//
//            transition.play();
//            System.out.println("Vehicle " + vehicleId + " is moving to the intersection.");
//        });
//    }
//
//    public void cruzarInterseccion(String vehicleId, String direction) {
//        Platform.runLater(() -> {
//            Rectangle vehiculo = vehiculos.get(vehicleId);
//            if (vehiculo == null) return;
//
//            TranslateTransition crossTransition = new TranslateTransition(Duration.seconds(5), vehiculo);
//
//            switch (direction) {
//                case "N":
//                    crossTransition.setByY(-interseccion.getY() - vehiculo.getHeight() * 100);
//                    break;
//                case "S":
//                    crossTransition.setByY(interseccion.getY() + interseccion.getHeight() * 100);
//                    break;
//                case "E":
//                    crossTransition.setByX(interseccion.getX() + interseccion.getWidth() * 100);
//                    break;
//                case "W":
//                    crossTransition.setByX(-interseccion.getX() - vehiculo.getWidth() * 100);
//                    break;
//            }
//
//            crossTransition.play();
//            System.out.println("Vehicle " + vehicleId + " is crossing the intersection.");
//        });
//    }
}
