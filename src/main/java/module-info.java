module com.example.poyectofinalparalela {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.poyectofinalparalela to javafx.fxml;
    exports com.example.poyectofinalparalela;
}