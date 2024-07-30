package com.example.poyectofinalparalela.visuales;

import com.example.poyectofinalparalela.transito.TrafficLight;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ControladorEscenario2 {


        @FXML
        private ImageView imageView;
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
        private Button Btn_normal_norte;
        @FXML
        private Button Btn_normal_sur;
        @FXML
        private Button Btn_emergencia_norte;
        @FXML
        private Button Btn_emergencia_sur;

        private List<TrafficLight> upperLights;
        private List<TrafficLight> lowerLights;

    @FXML
    private void handleBtnNormalNorteAction() {
        // Handle action for Btn_normal_norte
    }

    @FXML
    private void handleBtnEmergenciaNorteAction() {
        // Handle action for Btn_emergecia_norte
    }

    @FXML
    private void handleBtnNormalSurAction() {
        // Handle action for Btn_normal_sur
    }

    @FXML
    private void handleBtnEmergenciaSurAction() {
        // Handle action for Btn_emergecia_sur
    }

    private void changeTrafficLights() {
        // New thread to change the traffic lights every 15 seconds, if the upper lights are green, the lower lights will be red and vice versa
        Thread controlThread = new Thread(() -> {
            boolean upperGreen = true;
            while(true){
                if(upperGreen){
                    setGreenLights(upperLights, true);
                    setGreenLights(lowerLights, false);
                } else {
                    setGreenLights(upperLights, false);
                    setGreenLights(lowerLights, true);
                }
                upperGreen = !upperGreen;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        });

        controlThread.start();
    }

    private void setGreenLights(List<TrafficLight> lights, boolean green) {
        for (TrafficLight light : lights) {
            light.setGreen(green);
            //Aqui va el metodo de cambiar la luz en la vista
            //Tambien los carros de esa calle se mueven
        }
    }
}
