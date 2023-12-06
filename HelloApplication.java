package com.example.spaceinvaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        HelloController helloController = fxmlLoader.getController();
        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.W)
                HelloController.up =true;
            if(e.getCode() == KeyCode.S )
                HelloController.down =true;
            if (e.getCode() == KeyCode.D)
                HelloController.right = true;
            if (e.getCode() == KeyCode.A)
                HelloController.left = true;
            if(e.getCode() == KeyCode.E) {
                helloController.shoot();
            }
        });

        scene.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.W)
                HelloController.up =false;
            if(e.getCode() == KeyCode.S )
                HelloController.down =false;
            if (e.getCode() == KeyCode.D)
                HelloController.right = false;
            if (e.getCode() == KeyCode.A)
                HelloController.left = false;

            if (e.getCode() == KeyCode.R)
                HelloController.isPause =  !HelloController.isPause;
        });

        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}