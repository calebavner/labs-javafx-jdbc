module com.midasvision.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.midasvision.javafxjdbc to javafx.fxml;
    exports com.midasvision.javafxjdbc;
}