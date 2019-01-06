package com.joklek.pointexplorer.shape;

import org.springframework.lang.NonNull;

import java.util.UUID;

// Should circle use ellipse logic?
public class Circle implements Shape {

    private UUID id;
    private final Point center;
    private final double radius;
    private final double area;

    public Circle(UUID id, Point center, double radius) {
        this(center, radius);
        this.id = id;
    }

    Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.area = Math.PI * Math.pow(radius, 2);
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
        // sqrt((xp-xc)^2 + (yp-yc)^2)
        double distanceFromCenter = Math.sqrt(Math.pow(center.getX() - x, 2) + Math.pow(center.getY() - y, 2));
        return distanceFromCenter < radius;
    }
}
