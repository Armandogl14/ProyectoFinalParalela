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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        controladorVista = loader.getController();

        Scene scene = new Scene(root, 1420, 700);
        stage.setTitle("Control de Tráfico");
        stage.setScene(scene);
        stage.show();


        // Dejar la simulación correr por un tiempo y luego detenerla
    }


    public static void main(String[] args) {
        launch(args);
    }
}
