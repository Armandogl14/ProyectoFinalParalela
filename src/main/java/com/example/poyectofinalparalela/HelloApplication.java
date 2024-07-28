package com.example.poyectofinalparalela;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/poyectofinalparalela/seleccionador.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Control de Tráfico");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}