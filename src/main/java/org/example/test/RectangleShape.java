package org.example.test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class RectangleShape extends CreateShape {
    private final double width;
    private final double height;
    private final Color color;

    public RectangleShape(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public Shape create() {
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setFill(color);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStroke(Color.TRANSPARENT);
        return rectangle;
    }
}