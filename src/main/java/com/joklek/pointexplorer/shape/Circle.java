package com.joklek.pointexplorer.shape;

import com.joklek.pointexplorer.Point;

import java.util.UUID;

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
    public UUID getId() {
        return id;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public boolean doesIncludePoint(Point point) {
        return doesIncludePoint(point.getX(), point.getY());
    }

    @Override
    public boolean doesIncludePoint(double x, double y) {
        // sqrt((xp-xc)^2 + (yp-yc)^2)
        double distanceFromCenter = Math.sqrt(Math.pow(center.getX() - x, 2) + Math.pow(center.getY() - y, 2));
        return distanceFromCenter < radius;
    }
}
