package com.joklek.pointexplorer.shape;

import org.springframework.lang.NonNull;

import java.util.UUID;

public class Ellipse implements Shape {

    private UUID id;
    private final Point center;
    private final double radius1;
    private final double radius2;
    private final double area;

    public Ellipse(UUID id, Point center, double radius1, double radius2) {
        this(center, radius1, radius2);
        this.id = id;
    }

    Ellipse(Point center, double radius1, double radius2) {
        this.center = center;
        if(radius1 <= 0) {
            throw new IllegalArgumentException("Radius must be positive, but is " + radius1);
        }
        else {
            this.radius1 = radius1;
        }
        if(radius2 <= 0) {
            throw new IllegalArgumentException("Radius must be positive, but is " + radius2);
        }
        else {
            this.radius2 = radius2;
        }
        this.area = Math.PI * radius1 * radius2;
    }

    void setId(UUID id) {
        this.id = id;
    }

    @Override
    @NonNull
    public UUID getId() {
        return id;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public boolean doesIncludePoint(double x, double y) {
        double distanceFromCenter = Math.pow(x - center.getX(), 2)/radius1 + Math.pow(y - center.getY(), 2) / radius2;
        return distanceFromCenter <= 1;
    }

    @Override
    public String toString() {
        return "Ellipse{" +
                "id=" + id +
                ", x center=" + center.getX() +
                ", y center=" + center.getY() +
                ", radius1=" + radius1 +
                ", radius2=" + radius2 +
                ", area=" + area +
                '}';
    }
}
