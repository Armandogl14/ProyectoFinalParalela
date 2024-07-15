package com.example.poyectofinalparalela;

import com.example.poyectofinalparalela.transito.Intersection;
import com.example.poyectofinalparalela.transito.TrafficLight;
import com.example.poyectofinalparalela.transito.Vehicle;
import com.example.poyectofinalparalela.visuales.ControladorVista;
import com.example.poyectofinalparalela.visuales.ImputVisual;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private List<TrafficLight> semaforos = new ArrayList<>();
    private List<Intersection> calles = new ArrayList<>();
    private static ControladorVista controladorVista;
    private static ImputVisual imputVisual = new ImputVisual();
    int cantidadVehiculos ;

    @Override
    public void start(Stage stage) throws IOException {
        //abrirIngresarDatos();
        //inicializarSemaforosYCalles();
        //Trafico_vista(stage);
//        Trafico_vista(stage);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//        FXMLLoader inicio = new FXMLLoader(getClass().getResource("IngresarDatos.fxml"));
//        Parent root = loader.load();
//        controladorVista = loader.getController();
//
//        Scene scene = new Scene(root, 1420, 700);
//        stage.setTitle("Control de Tráfico");
//        stage.setScene(scene);
//        stage.show();
//
//        TrafficController controller = new TrafficController(semaforos, calles, controladorVista);
//
////         Iniciar el control de tráfico
//        controller.startControl();

        // Dejar la simulación correr por un tiempo y luego detenerla
    }
    /*public void abrirIngresarDatos() {
        try {
            // Carga el archivo FXML para el nuevo escenario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IngresarDatos.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ingresar Datos");
            stage.setScene(new Scene(root, 600, 400)); // Ajusta el tamaño según sea necesario
            stage.show();
            cantidadVehiculos = imputVisual.getSpinnerValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Trafico_vista(Stage stage) {
        try {
            // Carga el archivo FXML para el nuevo escenario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            controladorVista = loader.getController();
//            controladorVista.initialize(calles);
            controladorVista.initialize(calles);
        Scene scene = new Scene(root, 1420, 700);
        stage.setTitle("Control de Tráfico");
        stage.setScene(scene);
        stage.show();

        TrafficController controller = new TrafficController(semaforos, calles, controladorVista);

//         Iniciar el control de tráfico
        controller.startControl();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vehicle generateRandomVehicle() {
        Random random = new Random();
        // Genera un ID aleatorio para el vehículo
        String id = String.valueOf(random.nextInt(10));
        // Genera un estado aleatorio para el semáforo
        boolean signal = random.nextBoolean();
        // Genera una dirección aleatoria para el vehículo
        String[] directions = {"N", "S", "E", "W"};
        String direction = directions[random.nextInt(directions.length)];

        return new Vehicle(id, signal, direction);
    }

    public void addRandomVehiclesToStreet(Intersection street, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Vehicle vehicle = generateRandomVehicle();
            System.out.println("Vehicle " + vehicle.getId() + " added to street " + street.getId());
            street.addVehicle(vehicle);
        }
    }

    private void inicializarSemaforosYCalles() {
        // Crear semáforos
        TrafficLight trafficLight1 = new TrafficLight("0");
        TrafficLight trafficLight2 = new TrafficLight("1");
        TrafficLight trafficLight3 = new TrafficLight("2");
        TrafficLight trafficLight4 = new TrafficLight("3");

        semaforos.add(trafficLight1);
        semaforos.add(trafficLight2);
        semaforos.add(trafficLight3);
        semaforos.add(trafficLight4);

        // Crear calles y agregar vehículos
        Intersection street1 = new Intersection("N");
        Vehicle vehicle = new Vehicle("1", false, "S");
        street1.addVehicle(vehicle);
        Vehicle vehicle2 = new Vehicle("2", false, "S");
        street1.addVehicle(vehicle2);
        Vehicle vehicle3 = new Vehicle("3", false, "S");
        street1.addVehicle(vehicle3);
//        addRandomVehiclesToStreet(street1, cantidadVehiculos);
        calles.add(street1);

        Intersection street2 = new Intersection("E");
        Vehicle vehicle4 = new Vehicle("4", true, "W");
        street2.addVehicle(vehicle4);
        Vehicle vehicle5 = new Vehicle("5", false, "W");
        street2.addVehicle(vehicle5);
//        addRandomVehiclesToStreet(street2, cantidadVehiculos);
        calles.add(street2);

        Intersection street3 = new Intersection("S");
        Vehicle vehicle6 = new Vehicle("6", false, "N");
        street3.addVehicle(vehicle6);
        Vehicle vehicle7 = new Vehicle("7", false, "N");
        street3.addVehicle(vehicle7);
//        addRandomVehiclesToStreet(street3, cantidadVehiculos);
        calles.add(street3);

        Intersection street4 = new Intersection("W");
        Vehicle vehicle8 = new Vehicle("8", false, "E");
        street4.addVehicle(vehicle8);
//        addRandomVehiclesToStreet(street4, cantidadVehiculos);
        calles.add(street4);
    }*/

    public static void main(String[] args) {

        try {
            launch(args);
            ImputVisual.latch.await(); // Espera aquí hasta que el valor del Spinner sea obtenido
            // Continúa con la ejecución después de obtener el valor del Spinner
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
