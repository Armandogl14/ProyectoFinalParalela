//module com.example.poyectofinalparalela {
//    requires javafx.controls;
//    requires javafx.fxml;
//
//
//    opens com.example.poyectofinalparalela to javafx.fxml;
//    exports com.example.poyectofinalparalela.visuales to javafx.fxml;
//    exports com.example.poyectofinalparalela.transito;
//}
module com.example.poyectofinalparalela {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.poyectofinalparalela to javafx.fxml;
    opens com.example.poyectofinalparalela.visuales to javafx.fxml;
    exports com.example.poyectofinalparalela;
    exports com.example.poyectofinalparalela.visuales;
}