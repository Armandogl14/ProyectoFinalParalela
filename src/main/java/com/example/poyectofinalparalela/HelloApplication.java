package com.example.poyectofinalparalela;

import com.example.poyectofinalparalela.transito.Street;
import com.example.poyectofinalparalela.transito.TrafficController;
import com.example.poyectofinalparalela.transito.TrafficLight;
import com.example.poyectofinalparalela.visuales.ControladorVista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private List<TrafficLight> semaforos;
    private List<Street> calles;

    private TrafficLight trafficLight1;
    private TrafficLight trafficLight2;
    private TrafficLight trafficLight3;
    private TrafficLight trafficLight4;

    @Override
    public void start(Stage stage) throws IOException {
        inicializarSemaforosYCalles();
        ControladorVista controladorVista = new ControladorVista();

        // Crear la escena y añadir el panel principal
        Scene scene = new Scene(controladorVista.crearVista(), 1420, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        TrafficController trafficController = new TrafficController(null, semaforos, calles);
        trafficController.startControl();
        trafficController.manageIntersection();

        // Example call to change colors, ensure this is done after the scene is shown
//        controladorVista.cambiarColorSemaforoVerde(0); // Just an example, adjust as needed
    }

    private void inicializarSemaforosYCalles() {
        semaforos = new ArrayList<>();
        calles = new ArrayList<>();

        trafficLight1 = new TrafficLight("0");
        trafficLight2 = new TrafficLight("1");
        trafficLight3 = new TrafficLight("2");
        trafficLight4 = new TrafficLight("3");

        semaforos.add(trafficLight1);
        semaforos.add(trafficLight2);
        semaforos.add(trafficLight3);
        semaforos.add(trafficLight4);

        Street street1 = new Street("0", trafficLight1);
        calles.add(street1);
        Street street2 = new Street("1", trafficLight2);
        calles.add(street2);
        Street street3 = new Street("2", trafficLight3);
        calles.add(street3);
        Street street4 = new Street("3", trafficLight4);
        calles.add(street4);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
