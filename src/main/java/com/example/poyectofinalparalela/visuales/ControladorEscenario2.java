package com.example.poyectofinalparalela.visuales;

import com.example.poyectofinalparalela.transito.Interseccion_caso2;
import com.example.poyectofinalparalela.transito.TrafficLight;
import com.example.poyectofinalparalela.transito.Vehicle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ControladorEscenario2 {

    private Map<String, Rectangle> vehiculos = new HashMap<>();
    private ConcurrentLinkedQueue<Vehicle> vehicles = new ConcurrentLinkedQueue<>(); // prueba con este, no es tan jodon con la prioridad
    //vehiculos logicos y graficos de los principios
    private List<Vehicle> vehicleList = new LinkedList<>();
    //vehiculos logicos y graficos del medio
    private ConcurrentLinkedQueue<Vehicle> vehiclesMiddle = new ConcurrentLinkedQueue<>(); // prueba con este, no es tan jodon con la prioridad
    private List<Vehicle> vehicleListMiddle = new LinkedList<>();
    private Vehicle vehicle;

    //No esta usando por el momento
    private Interseccion_caso2 Intersection ;
    private Interseccion_caso2 Intersection2 ;
    //No esta usando por el momento

    @FXML
    private ImageView imageView;
    //Referencias
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
    private Rectangle ref_arriba_1;
    @FXML
    private Rectangle ref_arriba_2;
    @FXML
    private Rectangle ref_sur_1;
    @FXML
    private Rectangle ref_sur_2;
    //Referencias
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
    @FXML
    private Circle Semaforo_1;

    @FXML
    private Circle Semaforo_2;

    @FXML
    private Circle Semaforo_3;

    @FXML
    private Circle Semaforo_4;

    private List<TrafficLight> upperLights;
    private List<TrafficLight> lowerLights;

    public void initialize() {
        //Aqui se llama el constructor que manejes los hilos
        this.Intersection = new Interseccion_caso2("I1", true, this);
        this.Intersection2 = new Interseccion_caso2("I2", true, this);
        upperLights = new ArrayList<>();
        lowerLights = new ArrayList<>();
        TrafficLight light1 = new TrafficLight("L1");
        TrafficLight light2 = new TrafficLight("L2");
        upperLights.add(light1);
        upperLights.add(light2);
        TrafficLight light3 = new TrafficLight("L3");
        TrafficLight light4 = new TrafficLight("L4");
        lowerLights.add(light3);
        lowerLights.add(light4);
        changeTrafficLights();
    }
/*
   =
   =
   =
   =
   =
    ===================Metodos de los botones===================
 */
@FXML
private void handleBtnNormalNorteAction() {
    String direccion = getDireccion();
    Vehicle carro = new Vehicle("V" + Intersection.getvID(), false, direccion, "N", 666, 0, 30, 20, null);
    addVehicle(carro);
    //    Intersection.addVehicle(carro);
    //    manageIntersection(carro, Intersection);
}

    @FXML
    private void handleBtnEmergenciaNorteAction() {
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), true, direccion, "N", 666, 0, 30, 20, null);
        addVehicleVisual(carro);
        //    Intersection.addVehicle(carro);
        //    manageIntersection(carro, Intersection);
    }

    @FXML
    private void handleBtnNormalSurAction() {
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), false, direccion, "S", 838, 664, 30, 20, null);
        addVehicle(carro);
        //    Intersection.addVehicle(carro);
        //    manageIntersection(carro, Intersection);
    }

    @FXML
    private void handleBtnEmergenciaSurAction() {
        String direccion = getDireccion();
        Vehicle carro = new Vehicle("V" + Intersection.getvID(), true, direccion, "S", 838, 664, 30, 20, null);
        addVehicleVisual(carro);
        //    Intersection.addVehicle(carro);
        //    manageIntersection(carro, Intersection);
    }
    /*
       =
       =
       =
       =
       =
        ===================Metodos de los botones===================
     */
    private void changeTrafficLights() {
        Thread controlThread = new Thread(() -> {
            boolean upperGreen = true;
            while (true) {
                if (upperGreen) {
                    setGreenLights(upperLights, true);
                    setGreenLights(lowerLights, false);

                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getOrigin().equals("N")) {
                            manageIntersection(vehicle, Intersection);
                        }
                    }
                } else {
                    setGreenLights(upperLights, false);
                    setGreenLights(lowerLights, true);

                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getOrigin().equals("S")) {
                            manageIntersection(vehicle, Intersection2);
                        }
                    }
                }
                upperGreen = !upperGreen;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        controlThread.start();
    }

    private void setGreenLights(List<TrafficLight> lights, boolean green) {
        for (TrafficLight light : lights) {
            light.setGreen(green);
            System.out.println("Cambiando luz " + light.getId() + " a " + (green ? "verde" : "rojo"));
            if (green) {
                switch (light.getId()) {
                    case "L1":
                        System.out.println("Cambiando luz 1 a verde");
                        Semaforo_1.setFill(Color.GREEN);
                        Semaforo_2.setFill(Color.GREEN);
                        Semaforo_3.setFill(Color.RED);
                        Semaforo_4.setFill(Color.RED);
                        break;
                    case "L2":
                        System.out.println("Cambiando luz 2 a verde");
                        Semaforo_1.setFill(Color.GREEN);
                        Semaforo_2.setFill(Color.GREEN);
                        Semaforo_3.setFill(Color.RED);
                        Semaforo_4.setFill(Color.RED);
                        break;
                    case "L3":
                        System.out.println("Cambiando luz 3 a verde");
                        Semaforo_1.setFill(Color.RED);
                        Semaforo_2.setFill(Color.RED);
                        Semaforo_3.setFill(Color.GREEN);
                        Semaforo_4.setFill(Color.GREEN);
                        break;
                    case "L4":
                        System.out.println("Cambiando luz 4 a verde");
                        Semaforo_1.setFill(Color.RED);
                        Semaforo_2.setFill(Color.RED);
                        Semaforo_3.setFill(Color.GREEN);
                        Semaforo_4.setFill(Color.GREEN);
                        break;
                }
            }
            //Aqui va el metodo de cambiar la luz en la vista
            //Tambien los carros de esa calle se mueven
        }
    }

    private void handleEmergencyTrafficLights(Vehicle emergencyVehicle) {
        // Detect the intersection and change the lights accordingly
        if (emergencyVehicle.getOrigin().equals("N")) {
            setGreenLights(upperLights, true);
            setGreenLights(lowerLights, false);
        } else if (emergencyVehicle.getOrigin().equals("S")) {
            setGreenLights(upperLights, false);
            setGreenLights(lowerLights, true);
        }
    }


    private void manageIntersection(Vehicle vehicle, Interseccion_caso2 intersection) {
        if (vehicle.isEmergency()) {
            intersection.handleEmergency(vehicle);
        } else {
            intersection.handleNormal(vehicle);
        }
    }

    private void crossIntersection(Vehicle vehicle) {
        // Lógica para animar el cruce de la intersección
//        crussingVisual(vehicle);
        System.out.println("El vehículo " + vehicle.getId() + " ha cruzado la intersección.");
    }

    //================Aqui estan los metodos basicos================
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle " + vehicle.getId() + " added to the queue.");
        addVehicleVisual(vehicle);
    }
    public String getDireccion() {
        String[] directions = {"Izquierda", "Derecha", "Recto", "U-Turn", "Izquierda_2"};
        String direccion = directions[(int) (Math.random() * directions.length)];
        // Verificación adicional, innecesaria en este contexto pero útil para demostrar el enfoque
        return direccion != null ? direccion : "Recto"; // Devuelve "Izquierda" si, por alguna razón, se obtiene null
    }
    /*
   =
   =
   =
   =
   =
    ===================Metodos de los Visuales===================
 */
    public void addVehicleVisual(Vehicle vehicle) {
        // Crear el rectángulo que representa el vehículo
        Rectangle vehiculo;
        if (vehicle != null){
            if (vehicle.isEmergency()) {
                vehiculo = new Rectangle(vehicle.getSizeX(), vehicle.getSizeY(), Color.BLUE);
                vehiculo.setStroke(Color.RED);
                vehiculo.setStrokeWidth(2);
            } else {
                vehiculo = new Rectangle(vehicle.getSizeX(), vehicle.getSizeY(), Color.RED);
            } 
        } else {
            vehiculo = null;
        }

        System.out.println("Vehículo creado: " + vehiculo.getId());
        // Determinar la posición inicial del vehículo basado en su dirección
        double startX = 0;
        double startY = 0;
        switch (vehicle.getOrigin()) {
            case "N":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        startX = Ref_Norte_Centro.getLayoutX();
                        startY = Ref_Norte_Centro.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "Izquierda":
                        startX = Ref_Norte_izquierda.getLayoutX();
                        startY = Ref_Norte_izquierda.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "Izquierda_2":
                        startX = Ref_Norte_izquierda.getLayoutX();
                        startY = Ref_Norte_izquierda.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "Derecha":
                        startX = Ref_Norte_Derecha.getLayoutX();
                        startY = Ref_Norte_Derecha.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "U-Turn":
                        startX = Ref_Norte_izquierda.getLayoutX();
                        startY = Ref_Norte_izquierda.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                }
                break;
            case "S":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        startX = Ref_sur_Centro1.getLayoutX();
                        startY = Ref_sur_Centro1.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "Izquierda":
                        startX = Ref_sur_izquierda1.getLayoutX();
                        startY = Ref_sur_izquierda1.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "Izquierda_2":
                        startX = Ref_sur_izquierda1.getLayoutX();
                        startY = Ref_sur_izquierda1.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "Derecha":
                        startX = Ref_sur_Derecha1.getLayoutX();
                        startY = Ref_sur_Derecha1.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
                        break;
                    case "U-Turn":
                        startX = Ref_sur_izquierda1.getLayoutX();
                        startY = Ref_sur_izquierda1.getLayoutY();
                        vehicle.setX((int)startX);
                        vehicle.setY((int)startY);
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

        double endX = startX;
        System.out.println("StartX: " + startX +"endX" +endX);
        double endY = startY;
        System.out.println("StartY: " + startY +"endY" +endY);
        switch(vehicle.getOrigin()){
            case "N":
                endX = Interseccion_1.getLayoutX() ;

                break;
            case "S":
                endX = -Interseccion_1.getLayoutX();
                break;
        }
        transition.setByX(endX);
//        transition.setByY(endY);

        transition.play();

        // Verificar si el vehículo está en la intersección al finalizar la transición
        transition.setOnFinished(event -> {
            if (vehicle.getOrigin().equals("N")) {
                Intersection.handleNormal(vehicle);
            } else if (vehicle.getOrigin().equals("S")) {
                Intersection2.handleNormal(vehicle);
            }
//            pane.getChildren().remove(vehiculo);
//            vehicles.remove(vehicle);
            System.out.println("Vehículo removido del pane: " + vehicle.getId());
            crussingVisualIniciales(vehicle);
        });
    }



    public void crussingVisualIniciales(Vehicle vehicle) {
        Rectangle vehiculo = vehiculos.get(vehicle.getId());

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(vehiculo);
        transition.setDuration(Duration.seconds(2));

        double endX = vehicle.getX();
        double endY = vehicle.getY();
        switch (vehicle.getOrigin()) {
            case "N":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        endX = Interseccion_2.getLayoutX() - Interseccion_2.getWidth() * 2;
                        vehicle.setX((int) endX);
                        vehicleListMiddle.add(vehicle);
                        vehiclesMiddle.add(vehicle);
                        removeVehicle(vehicle.getId());
                        transition.setByX(endX);
                        break;
                    case "Izquierda_2":
                        endX = Interseccion_2.getLayoutX() - Interseccion_2.getWidth() * 2;
                        vehicle.setX((int) endX);
                        vehicleListMiddle.add(vehicle);
                        vehiclesMiddle.add(vehicle);
                        removeVehicle(vehicle.getId());
                        transition.setByX(endX);
                        break;
                    case "Izquierda":
                        endX = ref_arriba_2.getLayoutX()/4;
                        endY = ref_arriba_2.getLayoutY() - vehiculo.getLayoutY();
                        transition.setByX(endX);
                        transition.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        removeVehicle(vehicle.getId());
                        break;
                    case "Derecha":
                        endX = ref_sur_2.getLayoutX()/4;
                        endY = ref_sur_2.getLayoutY();
                        transition.setByX(endX);
                        transition.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        removeVehicle(vehicle.getId());
                        break;
                    case "U-Turn":
                        endX = Ref_Norte_Centro.getLayoutX();
                        endY = Ref_sur_izquierda1.getLayoutY();
                        transition.setByX(endX);
                        transition.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        removeVehicle(vehicle.getId());
                        break;
                }
                break;
            case "S":
                switch (vehicle.getDirection()) {
                    case "Recto":
                        endX = -Interseccion_2.getLayoutX() + Interseccion_2.getWidth() * 2;
                        vehicle.setX((int) endX);
                        vehicleListMiddle.add(vehicle);
                        vehiclesMiddle.add(vehicle);
                        removeVehicle(vehicle.getId());
                        transition.setByX(endX);
                        break;
                    case "Izquierda_2":
                        endX = -Interseccion_2.getLayoutX() + Interseccion_2.getWidth() * 2;
                        vehicle.setX((int) endX);
                        vehicleListMiddle.add(vehicle);
                        vehiclesMiddle.add(vehicle);
                        removeVehicle(vehicle.getId());
                        transition.setByX(endX);
                        break;
                    case "Izquierda":
                        endX = -ref_sur_1.getLayoutX()/9;
                        endY = ref_sur_1.getLayoutY() - vehiculo.getLayoutY();
                        transition.setByX(endX);
                        transition.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        removeVehicle(vehicle.getId());
                        break;
                    case "Derecha":
                        endX = -ref_arriba_1.getLayoutX()/9;
                        endY = ref_arriba_1.getLayoutY() - vehiculo.getLayoutY();
                        transition.setByY(endY);
                        transition.setByX(endX);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        removeVehicle(vehicle.getId());
                        break;
                    case "U-Turn":
                        endX = Ref_sur_izquierda1.getLayoutX() - vehiculo.getLayoutX();
                        endY = Ref_Norte_izquierda.getLayoutY() - vehiculo.getLayoutY();
                        transition.setByX(endX);
                        transition.setByY(endY);
                        vehicle.setX((int) endX);
                        vehicle.setY((int) endY);
                        removeVehicle(vehicle.getId());
                        break;
                }
                break;
        }
        transition.play();
    }
    /*
   =
   =
   =
   =
   =
    ===================Metodos de los Visuales===================
 */

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

    public void removeVehicle(String vehicleId) {
        // Remove from the list of vehicles
        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                vehicleToRemove = vehicle;
                break;
            }
        }
        if (vehicleToRemove != null) {
            vehicles.remove(vehicleToRemove);
            System.out.println("Vehicle " + vehicleId + " removed from the queue.");
        } else {
            System.out.println("Vehicle " + vehicleId + " not found in the queue.");
        }

        // Remove from the list of vehicleList
        vehicleToRemove = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId().equals(vehicleId)) {
                vehicleToRemove = vehicle;
                break;
            }
        }
        if (vehicleToRemove != null) {
            vehicleList.remove(vehicleToRemove);
            System.out.println("Vehicle " + vehicleId + " removed from the list.");
        } else {
            System.out.println("Vehicle " + vehicleId + " not found in the list.");
        }
    }



}
