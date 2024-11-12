package org.example.test;

import java.util.HashSet;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class ShapeMover {
    private static final double MOVE_DISTANCE = 5;
    private static final double RAD = 25;
    private static double BALL_SPEED = 0.7;
    private static int SCORE_P1 = 0;
    private static int SCORE_P2 = 0;
    private static double dx;
    private static double dy;
    private static final Set<KeyCode> activeKeys = new HashSet<>();

    public static void addMovementControls(Scene scene, Shape p1, Shape p2) {
        scene.setOnKeyPressed(event -> activeKeys.add(event.getCode()));
        scene.setOnKeyReleased(event -> activeKeys.remove(event.getCode()));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (activeKeys.contains(KeyCode.UP) && p1.getTranslateY() > 0) {
                    p1.setTranslateY(p1.getTranslateY() - MOVE_DISTANCE);
                }
                if (activeKeys.contains(KeyCode.DOWN)
                        && p1.getTranslateY() < scene.getHeight() - p1.getBoundsInParent().getHeight()) {
                    p1.setTranslateY(p1.getTranslateY() + MOVE_DISTANCE);
                }
                if (activeKeys.contains(KeyCode.W) && p2.getTranslateY() > 0) {
                    p2.setTranslateY(p2.getTranslateY() - MOVE_DISTANCE);
                }
                if (activeKeys.contains(KeyCode.S)
                        && p2.getTranslateY() < scene.getHeight() - p2.getBoundsInParent().getHeight()) {
                    p2.setTranslateY(p2.getTranslateY() + MOVE_DISTANCE);
                }
            }
        };
        timer.start();
    }

    public static void initializeMovement(double angleInDegrees) {
        double angleInRadians = Math.toRadians(angleInDegrees);
        System.out.println("Angle de départ : " + angleInRadians);
        dx = BALL_SPEED * Math.cos(angleInRadians);
        dy = BALL_SPEED * Math.sin(angleInRadians);
    }

    public static BooleanProperty startAutoMovement(Shape ball, Shape p1, Shape p2, double sceneWidth,
            double sceneHeight, Label scoreP1Label, Label scoreP2Label) {

        Timeline timeline = new Timeline();
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.001), (ActionEvent event) -> {
            double newX = ball.getTranslateX() + dx;
            double newY = ball.getTranslateY() + dy;

            if (newX < 0 - RAD) {
                SCORE_P1 += 1;
                scoreP1Label.setText("P1 Score: " + SCORE_P1);
                System.out.println("P2 loses");
                timeline.stop();
                gameOver.set(true);
                return;
            } else if (newX > sceneWidth + RAD) {
                SCORE_P2 += 1;
                scoreP2Label.setText("P2 Score: " + SCORE_P2);
                System.out.println("P1 loses");
                timeline.stop();
                gameOver.set(true);
                return;
            }

            if (newY - RAD <= 0 || newY + RAD > sceneHeight) {
                dy = -dy;
            }

            if (ball.getBoundsInParent().intersects(p1.getBoundsInParent())) {
                BALL_SPEED += 0;
                dx = -Math.abs(dx);
            } else if (ball.getBoundsInParent().intersects(p2.getBoundsInParent())) {
                BALL_SPEED += 0;
                dx = Math.abs(dx);
            }
            ball.setTranslateX(newX);
            ball.setTranslateY(newY);
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return gameOver;
    }
}
