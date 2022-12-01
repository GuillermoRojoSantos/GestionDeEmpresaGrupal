module com.mycompany.gestion {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires mysql.connector.java;
    requires java.sql;

    opens com.mycompany.gestion to javafx.fxml;
    opens model;
    exports com.mycompany.gestion;
}
