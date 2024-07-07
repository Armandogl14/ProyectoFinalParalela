package com.example.poyectofinalparalela.visuales;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ControladorVista {
    private Rectangle semaforoNoroeste = new Rectangle();
    private Rectangle semaforoNoreste = new Rectangle();
    private Rectangle semaforoSur_oeste = new Rectangle();
    private Rectangle semaforoSur_este = new Rectangle();

    public Pane crearVista() {
        Pane pane = new Pane();

        // Líneas que representan las calles
        Line line1 = new Line(0, 325, 1420, 325);
        line1.setStrokeWidth(70);
        Line line2 = new Line(0, 375, 1420, 375);
        line2.setStrokeWidth(70);
        Line line3 = new Line(685, 0, 685, 700);
        line3.setStrokeWidth(70);
        Line line4 = new Line(735, 0, 735, 700);
        line4.setStrokeWidth(70);

        // Inicializar los rectángulos que representan los semáforos
        semaforoNoroeste = new Rectangle(670, 310, 30, 30);
        semaforoNoroeste.setFill(Color.RED);
        semaforoNoreste = new Rectangle(720, 310, 30, 30);
        semaforoNoreste.setFill(Color.RED);
        semaforoSur_oeste = new Rectangle(670, 360, 30, 30);
        semaforoSur_oeste.setFill(Color.RED);
        semaforoSur_este = new Rectangle(720, 360, 30, 30);
        semaforoSur_este.setFill(Color.RED);

        pane.getChildren().addAll(line1, line2, line3, line4, semaforoNoroeste, semaforoNoreste, semaforoSur_oeste, semaforoSur_este);

        return pane;
    }

    public void cambiarColorSemaforoVerde(int semaforo) {
        System.out.println("Cambiando semáforo " + semaforo + " a verde");
        semaforoNoroeste.setFill(Color.GREEN);
        switch (semaforo) {
            case 0:
                semaforoNoroeste.setFill(Color.GREEN);
                break;
            case 1:
                semaforoNoreste.setFill(Color.GREEN);
                break;
            case 2:
                semaforoSur_oeste.setFill(Color.GREEN);
                break;
            case 3:
                semaforoSur_este.setFill(Color.GREEN);
                break;
        }
    }

    public void cambiarColorSemaforoRojo(int semaforo) {
        System.out.println("Cambiando semáforo a rojo: " + semaforo);
        switch (semaforo) {
            case 0:
                semaforoNoroeste.setFill(Color.RED);
                break;
            case 1:
                semaforoNoreste.setFill(Color.RED);
                break;
            case 2:
                semaforoSur_oeste.setFill(Color.RED);
                break;
            case 3:
                semaforoSur_este.setFill(Color.RED);
                break;
        }
    }
}