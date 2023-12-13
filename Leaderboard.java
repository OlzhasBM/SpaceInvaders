package com.example.spaceinvaders;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Leaderboard {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView im1;

    @FXML
    private ImageView im2;

    @FXML
    private Button returnbutton;
    private final int BG_Heigh = 500;
    private ParallelTransition parallelTransition;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> countC;

    @FXML
    private TableColumn<User, String> nickC;

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

        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getUser(new User());

        try {
            ObservableList<User> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String nickname = resultSet.getString("nickname");
                String count = resultSet.getString("count");
                System.out.println("Nickname: " + nickname + ", Count: " + count);
                data.add(new User(nickname, count));
            }

            countC.setCellValueFactory(new PropertyValueFactory<>("count"));
            nickC.setCellValueFactory(new PropertyValueFactory<>("nickname"));

            userTable.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
