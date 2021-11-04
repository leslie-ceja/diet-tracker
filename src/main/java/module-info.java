module com.example.diet_tracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;

    opens com.example.diet_tracker to javafx.fxml;
    exports com.example.diet_tracker;
}