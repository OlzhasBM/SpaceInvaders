package com.example.spaceinvaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OOO extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OOO.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

