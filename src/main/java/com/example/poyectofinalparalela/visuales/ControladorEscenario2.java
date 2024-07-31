package com.example.poyectofinalparalela.visuales;

import com.example.poyectofinalparalela.transito.Intersection;
import com.example.poyectofinalparalela.transito.TrafficLight;
import com.example.poyectofinalparalela.transito.Vehicle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ControladorEscenario2 {

    private Map<String, Rectangle> vehiculos = new HashMap<>();
    private ConcurrentLinkedQueue<Vehicle> vehicles = new ConcurrentLinkedQueue<>(); // prueba con este, no es tan jodon con la prioridad
    private List<Vehicle> vehicleList = new LinkedList<>();
    private Vehicle vehicle;
    private Intersection Intersection ;
    private Intersection Intersection2 ;

    @FXML
    private ImageView imageView;
    @FXML
    private Rectangle Ref_Norte_izquierda;
    @FXML
    private Rectangle Ref_Norte_Derecha;
    @FXML
    private Rectangle Ref_Norte_Centro;
    @FXML
    private Rectangle Ref_sur_izquierda1;
    @FXML
    private Rectangle Ref_sur_Derecha1;
    @FXML
    private Rectangle Ref_sur_Centro1;
    @FXML
    private Rectangle Interseccion_1;
    @FXML
    private Rectangle Interseccion_2;
    @FXML
    private Button Btn_normal_norte;
    @FXML
    private Button Btn_normal_sur;
    @FXML
    private Button Btn_emergencia_norte;
    @FXML
    private Button Btn_emergencia_sur;
    @FXML
    private Pane pane;

    private List<TrafficLight> upperLights;
    private List<TrafficLight> lowerLights;

    public void initialize() {
        //Aqui se llama el constructor que manejes los hilos
        this.Intersection = new Intersection("I1", true, null);
        this.Intersection2 = new Intersection("I2", true, null);
    }

    @FXML
    private void handleBtnNormalNorteAction() {
        // Handle action for Btn_normal_norte
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), false, direccion, "N", 666, 0, 30, 20, null);
//        addVehicle(carro);
        Intersection.addVehicle(carro);

    }

    @FXML
    private void handleBtnEmergenciaNorteAction() {
        // Handle action for Btn_emergecia_norte
        System.out.println("Botón norte emergencia presionado.");
        // Aquí va la lógica específica para este botón
        //como crea un carro en logico y lo visual y hacer un cambio de estado para saber que hay un vehiculo de emergencia
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), true, direccion, "N", 666, 0, 30, 20, null);
        //addVehicle(carro);
        Intersection.addVehicle(carro);
        while(!Intersection.isIntersectionFree()){
            System.out.println("La interseccion esta ocupada");
        }
        Intersection.handleEmergency("N");
    }

    @FXML
    private void handleBtnNormalSurAction() {
        // Handle action for Btn_normal_sur
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), false, direccion, "S", 838, 664, 30, 20, null);
        addVehicle(carro);
        Intersection.addVehicle(carro);
    }

    @FXML
    private void handleBtnEmergenciaSurAction() {
        // Handle action for Btn_emergecia_sur
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V"+ Intersection.getvID(), true, direccion, "S", 838, 664, 30, 20, null);
        addVehicle(carro);
        Intersection.addVehicle(carro);
        while(!Intersection.isIntersectionFree()){
            System.out.println("La interseccion esta ocupada");
        }
        Intersection.handleEmergency("S");
    }

    private void changeTrafficLights() {
        // New thread to change the traffic lights every 15 seconds, if the upper lights are green, the lower lights will be red and vice versa
        Thread controlThread = new Thread(() -> {
            boolean upperGreen = true;
            while(true){
                if(upperGreen){
                    setGreenLights(upperLights, true);
                    setGreenLights(lowerLights, false);
                } else {
                    setGreenLights(upperLights, false);
                    setGreenLights(lowerLights, true);
                }
                upperGreen = !upperGreen;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        });

        controlThread.start();
    }

    private void setGreenLights(List<TrafficLight> lights, boolean green) {
        for (TrafficLight light : lights) {
            light.setGreen(green);
            System.out.println("Cambiando luz " + light.getId() + " a " + (green ? "verde" : "rojo"));
            //Aqui va el metodo de cambiar la luz en la vista
            //Tambien los carros de esa calle se mueven
        }
    }
    //================Aqui estan los metodos basicos================
    public void addVehicle(Vehicle vehicle) {
        Vehicle lastVehicle = getLastVehicle();
        if (lastVehicle == null) {
            vehicles.add(vehicle);
            System.out.println("Vehicle " + vehicle.getId() + " added to the queue.");
            addVehicleVisual();
            vehicleList.add(vehicle);
            return;
        } else if (vehicle.getId() != lastVehicle.getId() ) {
            vehicles.add(vehicle);
            System.out.println("Vehicle " + vehicle.getId() + " added to the queue.");
            addVehicleVisual();
            vehicleList.add(vehicle);
        }else{
            System.out.println("Vehicle " + vehicle.getId() + " already in the queue.");
        }

    }
    public String getDireccion() {
        String[] directions = {"Izquierda", "Derecha", "Recto", "U-Turn"};
        String direccion = directions[(int) (Math.random() * directions.length)];
        // Verificación adicional, innecesaria en este contexto pero útil para demostrar el enfoque
        return direccion != null ? direccion : "Recto"; // Devuelve "Izquierda" si, por alguna razón, se obtiene null
    }

    public void addVehicleVisual() {
        //Aqui va el metodo de agregar un carro a la vista
        if (!vehicleList.isEmpty()) {
            Vehicle vehicle = vehicleList.get(vehicleList.size() - 1); // Tomamos el último vehículo añadido
            Rectangle vehiculo = null;
            if (vehicle != null) {
                // Crear el rectángulo que representa el vehículo
                if (vehicle.isEmergency()) {
                    vehiculo = new Rectangle(vehicle.getSizeX(), vehicle.getSizeY(), Color.BLUE);
                    vehiculo.setStroke(Color.RED);
                    vehiculo.setStrokeWidth(2);
                } else {
                    vehiculo = new Rectangle(vehicle.getSizeX(), vehicle.getSizeY(), Color.RED);
                }

                // Determinar la posición inicial del vehículo basado en su dirección
                double startX = 0;
                double startY = 0;
                switch (vehicle.getOrigin()) {
                    case "N":
                        switch (vehicle.getDirection()) {
                            case "Recto":
                                startX = Ref_Norte_Centro.getLayoutX();
                                startY = Ref_Norte_Centro.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                            case "Izquierda":
                                startX = Ref_Norte_izquierda.getLayoutX();
                                startY = Ref_Norte_izquierda.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                            case "Derecha":
                                startX = Ref_Norte_Derecha.getLayoutX();
                                startY = Ref_Norte_Derecha.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                            case "U-Turn":
                                startX = Ref_Norte_izquierda.getLayoutX();
                                startY = Ref_Norte_izquierda.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                        }
                        break;
                    case "S":
                        switch (vehicle.getDirection()) {
                            case "Recto":
                                startX = Ref_sur_Centro1.getLayoutX();
                                startY = Ref_sur_Centro1.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                            case "Izquierda":
                                startX = Ref_sur_izquierda1.getLayoutX();
                                startY = Ref_sur_izquierda1.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                            case "Derecha":
                                startX = Ref_sur_Derecha1.getLayoutX();
                                startY = Ref_sur_Derecha1.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                            case "U-Turn":
                                startX = Ref_sur_izquierda1.getLayoutX();
                                startY = Ref_sur_izquierda1.getLayoutY();
                                vehicle.setX((int) startX);
                                vehicle.setY((int) startY);
                                break;
                        }
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

                double endX = 0;
                double endY = 0;
                switch(vehicle.getOrigin()){
                    case "N":
                        endY = Interseccion_1.getLayoutX() - startX - vehicle.getSizeX();
                        vehicle.setY((int) endY);
                        break;
                    case "S":
                        endY = Interseccion_2.getLayoutX() + Interseccion_2.getHeight() - startX;
                        vehicle.setY((int) endY);
                        break;
                }
                transition.setByX(endX);
                transition.setByY(endY);

                transition.play();
                //=============
                // Verificar si el vehículo está en la intersección
                transition.setOnFinished(event -> {
//                    if (isVehicleInIntersection(vehicle)) {
                    // Lógica específica si el vehículo está en la intersección
//                        System.out.println("El vehículo está en la intersección.");
                    while (!Intersection.isIntersectionFree()) {
//                            System.out.println("La intersección está ocupada.");
                    }
//                        System.out.println("El vehiculo" + vehicle.getId() + " esta cruzando la interseccion");
                    Intersection.setIntersectionFree(false);
                    //Animacion de cruzar la interseccion
//                    crussingVisual(vehicle);
                    Intersection.setIntersectionFree(true);
                    //sacar vehiculos de la cola (quitalo si da problemas)
//                        System.out.println("El vehiculo" + vehicle.getId() + " ha cruzado la interseccion");
                    Intersection.getVehicles().remove(vehicle);
                    vehicles.remove(vehicle);
//                    }
                });
            }
        }
    }
    public void crussingVisual(Vehicle vehicle) {
        Rectangle vehiculo = vehiculos.get(vehicle.getId());

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(vehiculo);
        transition.setDuration(Duration.seconds(2));

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(vehiculo);
        transition2.setDuration(Duration.seconds(1));

        double endX = vehicle.getX();
        double endY = vehicle.getY();
//        switch (vehicle.getOrigin()) {
//            case "N":
//                switch (vehicle.getDirection()) {
//                    case "Recto":
//                        endY = Referenciia_sur.getLayoutY() - vehiculo.getLayoutY();
//                        transition.setByY(endY);
//                        break;
//                    case "Izquierda":
//                        endX = Referenciia_este.getLayoutX() - vehiculo.getLayoutX();
//                        transition.setByX(endX);
//                        transition2.setByY(Referenciia_este.getLayoutY() / 2);
//                        transition2.play();
//                        break;
//                    case "Derecha":
//                        endX = Referenciia_oeste.getLayoutX() - vehiculo.getLayoutX();
//                        transition.setByX(endX);
//                        transition2.setByY(Referenciia_oeste.getLayoutY() / 4);
//                        transition2.play();
//                        break;
//                    case "U-Turn":
//                        endY = Interseccion_1.getHeight() * 2;
//                        transition.setByY(Referenciia_norte.getLayoutY() * 2);
//                        transition2.setByY(Referenciia_norte.getLayoutX() + 4);
//                        transition2.play();
//                        break;
//                }
//                break;
//            case "S":
//                switch (vehicle.getDirection()) {
//                    case "Recto":
//                        System.out.println("Referencia sur: " + Referenciia_norte.getY() + "========================================");
//                        endY = Referenciia_norte.getLayoutY() - vehiculo.getLayoutY();
//                        transition.setByY(endY);
//                        break;
//                    case "Izquierda":
//                        endX = Referenciia_oeste.getLayoutX() - vehiculo.getLayoutX();
//                        transition.setByX(endX);
//                        transition2.setByY(-Llegada_oeste.getLayoutY() / 2);
//                        transition2.play();
//                        break;
//                    case "Derecha":
//                        endX = Referenciia_este.getLayoutX() - vehiculo.getLayoutX();
//                        transition.setByX(endX);
//                        transition2.setByY(-Llegada_este.getLayoutY() / 4);
//                        transition2.play();
//                        break;
//                    case "U-Turn":
//                        endY = -Interseccion_2.getHeight() * 2;
//                        transition.setByY(Referenciia_sur.getLayoutY() * 20);
//                        transition2.setByY(-Referenciia_sur.getLayoutY() * 2);
//                        transition2.play();
//                        break;
//                }
//                break;
//        }
//        transition.play();
    }


    private Vehicle getLastVehicleInOrigin(String origin) {
        Stack<Vehicle> stack = new Stack<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOrigin().equals(origin)) {
                stack.push(vehicle);
            }
        }
        return stack.isEmpty() ? null : stack.pop();
    }

    public Vehicle getLastVehicle() {
        if (vehicles.isEmpty()) {
            return null; // Si no hay vehículos, no hay último vehículo
        }
        List<Vehicle> vehicleList = new ArrayList<>(vehicles);
        return vehicleList.get(vehicleList.size() - 1); // Devuelve el último vehículo
    }
}
