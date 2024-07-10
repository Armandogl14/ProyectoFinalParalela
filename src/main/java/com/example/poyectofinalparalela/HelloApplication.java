package com.example.poyectofinalparalela;

import com.example.poyectofinalparalela.transito.Street;
import com.example.poyectofinalparalela.transito.TrafficController;
import com.example.poyectofinalparalela.transito.TrafficLight;
import com.example.poyectofinalparalela.transito.Vehicle;
import com.example.poyectofinalparalela.visuales.ControladorVista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private List<TrafficLight> semaforos = new ArrayList<>();
    private List<Street> calles = new ArrayList<>();
    private static ControladorVista controladorVista;

    @Override
    public void start(Stage stage) throws IOException {
        inicializarSemaforosYCalles();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        controladorVista = loader.getController();

        Scene scene = new Scene(root, 1420, 700);
        stage.setTitle("Control de Tráfico");
        stage.setScene(scene);
        stage.show();

//        controladorVista.moverVehiculo("1", "S");
//        controladorVista.moverVehiculo("2", "N");
//        controladorVista.moverVehiculo("3", "E");



        TrafficController controller = new TrafficController(semaforos, calles, controladorVista);

//         Iniciar el control de tráfico
        controller.startControl();

        // Dejar la simulación correr por un tiempo y luego detenerla
//        try {
//            Thread.sleep(20000); // Dejar correr por 20 segundos
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Detener el control de tráfico
//        controller.stopControl();
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
        Street street1 = new Street("N");
        street1.addVehicle(new Vehicle("1", true, "S"));
        street1.addVehicle(new Vehicle("2", true, "S"));
        street1.addVehicle(new Vehicle("3", false, "S"));
        calles.add(street1);

        Street street2 = new Street("E");
        street2.addVehicle(new Vehicle("4", false, "W"));
        street2.addVehicle(new Vehicle("5", false, "W"));
        calles.add(street2);

        Street street3 = new Street("S");
        street3.addVehicle(new Vehicle("6", false, "N"));
        street3.addVehicle(new Vehicle("7", false, "N"));
        calles.add(street3);

        Street street4 = new Street("W");
        street4.addVehicle(new Vehicle("8", false, "E"));
        calles.add(street4);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
