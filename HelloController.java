package com.example.spaceinvaders;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView im1, im2, ship, enemy,enemy1,enemy11,enemy111,bug,bullet,explotion;

    @FXML
    private Label labelPause;

    @FXML
    private Label labelLose;

    @FXML
    private Label labelCount;
    private final int BG_Heigh = 500;

    private ParallelTransition parallelTransition;

    public static boolean up = false;
    public static boolean down = false;
    public static boolean right = false;
    public static boolean left = false;
    public static boolean isPause = false;
    private boolean enemyMovingRight = true;
    private double enemySpeed = 0.55;
    private boolean enemy1MovingRight = true;
    private double enemy1Speed = 0.55;
    private boolean enemy11MovingRight = true;
    private double enemy11Speed = 3.99;
    private boolean enemy111MovingRight = true;
    private double enemy111Speed = 3.99;
    private int shipspeed = 3;
    private int destroyedCount = 0;
    private Timeline enemyRespawnTimer;

    @FXML
    private Button restartButton;

    @FXML
    private Button result;

    @FXML
    private Button register;


    private boolean bulletVisible = false;

    AnimationTimer enemyMovementTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(enemyMovingRight){
                if(enemy.getLayoutX() < 200f){
                    enemy.setLayoutX(enemy.getLayoutX() + enemySpeed);
                }
                else {
                    enemyMovingRight = false;
                }
            }
            else {
                if(enemy.getLayoutX() > 41f){
                    enemy.setLayoutX(enemy.getLayoutX() -enemySpeed);
                }
                else{
                    enemyMovingRight = true;
                }
            }
        }
    };

    AnimationTimer enemy1MovementTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(enemy1MovingRight){
                if(enemy1.getLayoutX() < 200f){
                    enemy1.setLayoutX(enemy1.getLayoutX() + enemy1Speed);
                }
                else {
                    enemy1MovingRight = false;
                }
            }
            else {
                if(enemy1.getLayoutX() > 41f){
                    enemy1.setLayoutX(enemy1.getLayoutX() -enemy1Speed);
                }
                else{
                    enemy1MovingRight = true;
                }
            }
        }
    };

    AnimationTimer enemy11MovementTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(enemy11MovingRight){
                if(enemy11.getLayoutY() < BG_Heigh - enemy11.getBoundsInParent().getHeight()){
                    enemy11.setLayoutY(enemy11.getLayoutY() + enemy11Speed);
                }
                else {
                    enemy11MovingRight = false;
                }
            }
            else {
                if(enemy11.getLayoutY() > 0){
                    enemy11.setLayoutY(enemy11.getLayoutY() - enemy11Speed);
                }
                else{
                    enemy11MovingRight = true;
                }
            }
        }
    };

    AnimationTimer enemy111MovementTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(enemy111MovingRight){
                if(enemy111.getLayoutY() < BG_Heigh - enemy111.getBoundsInParent().getHeight()){
                    enemy111.setLayoutY(enemy111.getLayoutY() + enemy111Speed);
                }
                else {
                    enemy111MovingRight = false;
                }
            }
            else {
                if(enemy111.getLayoutY() > 0){
                    enemy111.setLayoutY(enemy111.getLayoutY() - enemy111Speed);
                }
                else{
                    enemy111MovingRight = true;
                }
            }
        }
    };

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (up && ship.getLayoutY() > 0f )
                ship.setLayoutY(ship.getLayoutY() - shipspeed);
            if (down && ship.getLayoutY() < 450f)
                ship.setLayoutY(ship.getLayoutY() + shipspeed);

            if (right && ship.getLayoutX() < 350f)
                ship.setLayoutX(ship.getLayoutX() + shipspeed);
            if (left && ship.getLayoutX() > 28f)
                ship.setLayoutX(ship.getLayoutX() - shipspeed);

            if(isPause && !labelPause.isVisible()){
                shipspeed = 0;
                enemySpeed = 0;
                enemy1Speed = 0;
                enemy11Speed = 0;
                enemy111Speed = 0;
                parallelTransition.pause();
                labelPause.setVisible(true);
                restartButton.setVisible(true);

            }

            else if(!isPause && labelPause.isVisible()){
                shipspeed = 3;
                enemySpeed = 0.55;
                enemy1Speed = 0.55;
                enemy11Speed = 3.99;
                enemy111Speed = 3.99;
                parallelTransition.play();
                labelPause.setVisible(false);
                restartButton.setVisible(false);
            }

            if(ship.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                labelLose.setVisible(true);
                shipspeed = 0;
                enemySpeed = 0;
                enemy1Speed = 0;
                enemy11Speed = 0;
                enemy111Speed = 0;
                parallelTransition.pause();
            }
            if(ship.getBoundsInParent().intersects(enemy1.getBoundsInParent())){
                labelLose.setVisible(true);
                shipspeed = 0;
                enemySpeed = 0;
                enemy1Speed = 0;
                enemy11Speed = 0;
                enemy111Speed = 0;
                parallelTransition.pause();
            }
            if(ship.getBoundsInParent().intersects(enemy11.getBoundsInParent())){
                labelLose.setVisible(true);
                shipspeed = 0;
                enemySpeed = 0;
                enemy1Speed = 0;
                enemy11Speed = 0;
                enemy111Speed = 0;
                parallelTransition.pause();
            }
            if(ship.getBoundsInParent().intersects(enemy111.getBoundsInParent())){
                labelLose.setVisible(true);
                shipspeed = 0;
                enemySpeed = 0;
                enemy1Speed = 0;
                enemy11Speed = 0;
                enemy111Speed = 0;
                parallelTransition.pause();
            }

            checkCollision();
            labelCount.setText("Destroyed: " + destroyedCount);

        }
    };
    @FXML
    void initialize() {
        double scrolldistance = BG_Heigh;

        TranslateTransition bg0 = new TranslateTransition(Duration.millis(5000),im1);
        bg0.setFromY(0);
        bg0.setToY(scrolldistance);
        bg0.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bg1 = new TranslateTransition(Duration.millis(5000),im2);
        bg1.setFromY(0);
        bg1.setToY(scrolldistance);
        bg1.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bg3 = new TranslateTransition(Duration.millis(3500),bug);
        bg3.setFromY(0);
        bg3.setToY(scrolldistance);
        bg3.setInterpolator(Interpolator.LINEAR);
        bg3.setCycleCount(Animation.INDEFINITE);
        //bg3.play();

        parallelTransition = new ParallelTransition(bg0,bg1);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
        enemyMovementTimer.start();
        enemy1MovementTimer.start();
        enemy11MovementTimer.start();
        enemy111MovementTimer.start();
        restartButton.setOnAction(event -> restartGame());

        labelLose.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                restartButton.setVisible(true);
                register.setVisible(true);
                result.setVisible(true);
            } else {
                restartButton.setVisible(false);
                register.setVisible(false);
                result.setVisible(false);
            }
        });

        enemyRespawnTimer = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            enemy.setVisible(true);
            enemy1.setVisible(true);
            enemy11.setVisible(true);
            enemy111.setVisible(true);

            enemy.setLayoutX(60);
            enemy.setLayoutY(47);
            enemy1.setLayoutX(126);
            enemy1.setLayoutY(83);
            enemy11.setLayoutX(14);
            enemy11.setLayoutY(112);
            enemy111.setLayoutX(224);
            enemy111.setLayoutY(112);

        }));
        enemyRespawnTimer.setCycleCount(Animation.INDEFINITE);
        enemyRespawnTimer.play();

        register.setOnAction(event -> {
            register.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("register.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        result.setOnAction(event -> {
            result.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("leaderboard.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    @FXML
    void restartGame() {
        up = false;
        down = false;
        right = false;
        left = false;
        isPause = false;

        enemyMovingRight = true;
        enemySpeed = 1.5;
        enemy1MovingRight = true;
        enemy1Speed = 1.5;
        enemy11MovingRight = true;
        enemy11Speed = 2.99;
        enemy111MovingRight = true;
        enemy111Speed = 2.99;
        shipspeed = 3;

        ship.setLayoutX(119);
        ship.setLayoutY(417);
        enemy.setLayoutX(60);
        enemy.setLayoutY(47);
        enemy1.setLayoutX(126);
        enemy1.setLayoutY(83);
        enemy11.setLayoutX(14);
        enemy11.setLayoutY(112);
        enemy111.setLayoutX(224);
        enemy111.setLayoutY(112);

        enemy.setVisible(true);
        enemy1.setVisible(true);
        enemy11.setVisible(true);
        enemy111.setVisible(true);

        destroyedCount = 0;
        timer.start();
        enemyMovementTimer.start();
        enemy1MovementTimer.start();
        enemy11MovementTimer.start();
        enemy111MovementTimer.start();
        parallelTransition.play();
        labelLose.setVisible(false);

        enemyRespawnTimer.stop();
        enemyRespawnTimer.getKeyFrames().clear();

    }

    void shoot() {
        if (!bulletVisible) {
            bulletVisible = true;
            bullet.setLayoutX(ship.getLayoutX() + (ship.getBoundsInParent().getWidth() / 2) - (bullet.getBoundsInParent().getWidth() / 2));
            bullet.setLayoutY(ship.getLayoutY());
            bullet.setVisible(true);

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(19), event -> {
                bullet.setLayoutY(bullet.getLayoutY() - 5);

                if (bullet.getLayoutY() < 0) {
                    bulletVisible = false;
                    bullet.setVisible(false);
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            checkCollision();
        }
    }

    private void checkCollision() {
        if (bulletVisible) {
            if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                explotion.setLayoutX(enemy.getLayoutX());
                explotion.setLayoutY(enemy.getLayoutY());
                explotion.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    explotion.setVisible(false);
                });

                pause.play();
                enemy.setVisible(false);
                enemy.setLayoutX(-1000);
                enemy.setLayoutY(-1000);
                destroyedCount++;
            }
            if (bullet.getBoundsInParent().intersects(enemy1.getBoundsInParent())) {
                explotion.setLayoutX(enemy1.getLayoutX());
                explotion.setLayoutY(enemy1.getLayoutY());
                explotion.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    explotion.setVisible(false);
                });

                pause.play();
                enemy1.setVisible(false);
                enemy1.setLayoutX(-1000);
                enemy1.setLayoutY(-1000);
                destroyedCount++;
            }
            if (bullet.getBoundsInParent().intersects(enemy11.getBoundsInParent())) {
                explotion.setLayoutX(enemy11.getLayoutX());
                explotion.setLayoutY(enemy11.getLayoutY());
                explotion.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    explotion.setVisible(false);
                });

                pause.play();
                enemy11.setVisible(false);
                enemy11.setLayoutX(-1000);
                enemy11.setLayoutY(-1000);
                destroyedCount++;
            }
            if (bullet.getBoundsInParent().intersects(enemy111.getBoundsInParent())) {
                explotion.setLayoutX(enemy111.getLayoutX());
                explotion.setLayoutY(enemy111.getLayoutY());
                explotion.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    explotion.setVisible(false);
                });

                pause.play();
                enemy111.setVisible(false);
                enemy111.setLayoutX(-1000);
                enemy111.setLayoutY(-1000);
                destroyedCount++;
            }

            if (destroyedCount > 0) {
                enemyRespawnTimer.play();
            }

        }
    }

}

