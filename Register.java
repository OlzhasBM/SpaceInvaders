package com.example.spaceinvaders;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField counT;

    @FXML
    private Button enter;

    @FXML
    private ImageView im1;

    @FXML
    private ImageView im2;

    @FXML
    private TextField nicknameT;

    @FXML
    private Button returnbutton;
    private final int BG_Heigh = 500;
    private ParallelTransition parallelTransition;

    @FXML
    void initialize() {
        returnbutton.setOnAction(event -> {
            returnbutton.getScene().getWindow().hide();

            Stage mainStage = HelloApplication.getMainStage();
            if (mainStage != null) {
                mainStage.show();
            } else {
                System.err.println("Main stage reference is null.");
            }
        });

        double scrolldistance = BG_Heigh;

        TranslateTransition bg0 = new TranslateTransition(Duration.millis(5000),im1);
        bg0.setFromY(0);
        bg0.setToY(scrolldistance);
        bg0.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bg1 = new TranslateTransition(Duration.millis(5000),im2);
        bg1.setFromY(0);
        bg1.setToY(scrolldistance);
        bg1.setInterpolator(Interpolator.LINEAR);

        parallelTransition = new ParallelTransition(bg0,bg1);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        enter.setOnAction(event -> {
            singUpNewUser();

        });
    }
    private void singUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String nickmame = nicknameT.getText();
        String count = counT.getText();

        User user = new User(nickmame, count);
        dbHandler.signUpUser(user);
    }
}
