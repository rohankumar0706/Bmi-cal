module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires json.simple;
    requires org.junit.jupiter.api;

    opens org.openjfx.Controller to javafx.fxml;
    exports org.openjfx;
}