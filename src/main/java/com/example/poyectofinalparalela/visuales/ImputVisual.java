package com.example.poyectofinalparalela.visuales;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class ImputVisual {

    public static CountDownLatch latch;
    @FXML
    private Spinner<Integer> spinner = new Spinner<>(); // Cambiado a Spinner<Integer>
    private int valorSpinner;


    public void initialize() {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));
        spinner.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Integer value = spinner.getValue();
                if (value != null && value > 0) {
                    System.out.println("Valor guardado: " + value);
                    valorSpinner = value;
                    // Cerrar la ventana
                    Stage stage = (Stage) spinner.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }

    // Método para obtener el valor del Spinner
    public int getSpinnerValue() {
        return valorSpinner > 0 ? valorSpinner : 2;
    }
}