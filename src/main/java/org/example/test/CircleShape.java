package org.example.test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
         
public class CircleShape extends CreateShape {
   private final double radius;
   private final Color color;
   
   public CircleShape(double radius, Color color) {
       this.radius = radius;
       this.color = color;
   }
   
   @Override
    public Shape create() {
        Circle circle = new Circle(radius);
        circle.setStrokeType(StrokeType.OUTSIDE);
        circle.setStroke(Color.TRANSPARENT);
        circle.setFill(color);
        return circle;
    }
}
