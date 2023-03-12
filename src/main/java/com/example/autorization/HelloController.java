package com.example.autorization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;


public class HelloController {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void initialize() {


    }

    private double x = 0;
    private double y = 0;

    public void login() {
        Connection connect = database.connectDb();
        String sql = "SELECT * FROM userdata WHERE username = ? and password = ?";

        try {
            Alert alert;
            assert connect != null;
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            ResultSet result = prepare.executeQuery();

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Пожалуйста, заполните все пустые поля");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Информационное сообщение");
                    alert.setHeaderText(null);
                    alert.setContentText("Успешный вход в систему");
                    alert.showAndWait();

                    loginBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainWindow.fxml")));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Неправильное имя пользователя/Пароль");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        System.exit(0);
    }


}





