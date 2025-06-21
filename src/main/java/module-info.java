module com.example.greenkita2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.greenkita2 to javafx.fxml;
    exports com.example.greenkita2;
}