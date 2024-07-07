package com.example.poyectofinalparalela.visuales;

import com.example.poyectofinalparalela.transito.TrafficLight;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class VistaTrafico {

    private Stage stage;
    private Pane layout;
    private Circle[] semaforos = new Circle[4];
    Circle semaforoNoroeste = new Circle(685, 325, 10, Color.RED);
    public VistaTrafico() {
    }

    public void initUI(Stage stage) {
        this.stage = stage;
        this.layout = new Pane();

        // Dibujar calles
        int anchoVentana = 1420;
        int altoVentana = 700;

// Calcular puntos medios para posicionar las líneas y semáforos
        int puntoMedioX = anchoVentana / 2;
        int puntoMedioY = altoVentana / 2;

// Creating lines
        Line line1 = new Line(0, 325, 1420, 325);
        line1.setStrokeWidth(70);

        Line line2 = new Line(0, 375, 1420, 375);
        line2.setStrokeWidth(70);

        Line line3 = new Line(685, 0, 685, 700);
        line3.setStrokeWidth(70);

        Line line4 = new Line(735, 0, 735, 700);
        line4.setStrokeWidth(70);

// Creating circles (semaphores)
        Circle semaforoNoroeste = new Circle(685, 325, 10, Color.RED);
        semaforoNoroeste.setLayoutX(-45.0);
        semaforoNoroeste.setLayoutY(50.0);

        Circle semaforoNoreste = new Circle(735, 325, 10, Color.RED);
        semaforoNoreste.setLayoutX(45.0);

        Circle semaforoSur_oeste = new Circle(685, 375, 10, Color.RED);
        semaforoSur_oeste.setLayoutY(-95.0);

        Circle semaforoSur_este = new Circle(735, 375, 10, Color.RED);
        semaforoSur_este.setLayoutY(45.0);
// Crear la escena con el layout y las dimensiones de la ventana
        Scene scene = new Scene(layout, anchoVentana, altoVentana);
        stage.setScene(scene);
        stage.setTitle("Simulación de Intersección de Tráfico");
    }

    public void cambiarColorSemaforo(TrafficLight trafficLight) {
        semaforoNoroeste.setFill(Color.GREEN);
    }

}