package com.example.poyectofinalparalela.visuales;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Seleccionador {
    @FXML
    private Button escenario1Button;
    @FXML
    private Button escenario2Button;

    private ControladorVista controladorVista;

    public Seleccionador() {
        // No es necesario cargar el FXML aquí, JavaFX lo hace automáticamente
    }

    @FXML
    private void handleEscenario1ButtonAction() throws IOException {
        Stage stage = (Stage) escenario1Button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/poyectofinalparalela/hello-view.fxml"));
        Parent root = loader.load();
        controladorVista = loader.getController();

        Scene scene = new Scene(root, 1420, 700);
        stage.setTitle("Control de Tráfico");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleEscenario2ButtonAction() {
        // Handle action for Escenario 2 button
    }
}
