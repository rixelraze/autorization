module com.example.autorization {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.autorization to javafx.fxml;
    exports com.example.autorization;
}