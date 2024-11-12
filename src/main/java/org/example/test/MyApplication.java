package org.example.test;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class MyApplication extends Application {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 800;

    private Label scoreP1Label;
    private Label scoreP2Label;

    public void createScene(Stage stage) {
        scoreP1Label = new Label("P1 Score: 0");
        scoreP2Label = new Label("P2 Score: 0");

        scoreP1Label.setTranslateX(10);
        scoreP1Label.setTranslateY(10);
        scoreP1Label.setTextFill(Color.BLACK);

        scoreP2Label.setTranslateX(WIDTH - 100);
        scoreP2Label.setTranslateY(10);
        scoreP2Label.setTextFill(Color.BLACK);

        CreateShape circleShape = new CircleShape(15, Color.BLACK);
        CreateShape player1 = new RectangleShape(10, 100, Color.GREY);
        CreateShape player2 = new RectangleShape(10, 100, Color.GREY);

        Shape circle = circleShape.create();
        Shape p1 = player1.create();
        Shape p2 = player2.create();

        circle.setTranslateX(500);
        circle.setTranslateY(400);
        p1.setTranslateX(980);
        p1.setTranslateY(400);
        p2.setTranslateX(20);
        p2.setTranslateY(400);

        Group root = new Group(circle, p1, p2, scoreP1Label, scoreP2Label);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        ShapeMover.addMovementControls(scene, p1, p2);
        ShapeMover.initializeMovement(Math.random() * 360);
        BooleanProperty gameOver = ShapeMover.startAutoMovement(circle, p1, p2, WIDTH, HEIGHT, scoreP1Label,
                scoreP2Label);

        gameOver.addListener((obs, wasGameOver, isGameOver) -> {
            if (isGameOver) {
                startGame(stage, "Game is over, restart ?");
                System.out.println("Game is over");
            }
        });

        stage.setTitle("prout!");
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();
        p1.requestFocus();
        p2.requestFocus();
    }

    public void startGame(Stage stage, String title) {
        BorderPane root = new BorderPane();
        Scene restartScene = new Scene(root, WIDTH, HEIGHT);

        Button restartBtn = new Button();
        restartBtn.setText(title);
        root.setCenter(restartBtn);

        restartBtn.setOnAction(e -> {
            createScene(stage);
            System.out.println("Game started");
        });
        root.setCenter(restartBtn);

        stage.setTitle("prout!");
        stage.setScene(restartScene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        startGame(stage, "Start game");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
