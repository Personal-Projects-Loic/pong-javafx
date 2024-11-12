package org.example.test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareShape extends CreateShape {
    private final double size;
    private final Color color;

    public SquareShape(double size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public Shape create() {
        Rectangle rectangle = new Rectangle(size, size);
        rectangle.setFill(color);
        return rectangle;
    }
}
