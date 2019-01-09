package com.joklek.pointexplorer.shape;

import org.springframework.lang.NonNull;

import java.util.UUID;

public class Donut implements Shape {

    private UUID id;
    private final Point center;
    private final double holeRadius;
    private final double outerRadius;
    private final double area;

    public Donut(UUID id, Point center, double holeRadius, double outerRadius) {
        this(center, holeRadius, outerRadius);
        this.id = id;
    }

    public Donut(Point center, double holeRadius, double outerRadius) {
        if(holeRadius > outerRadius) {
            throw new IllegalArgumentException(String.format("Hole radius should be smaller than outer radius. %f is not smaller than %f", holeRadius, outerRadius));
        }

        this.center = center;
        if(holeRadius <= 0) {
            throw new IllegalArgumentException("Radius must be positive, but is " + holeRadius);
        }
        else {
            this.holeRadius = holeRadius;
        }
        if(outerRadius <= 0) {
            throw new IllegalArgumentException("Radius must be positive, but is " + outerRadius);
        }
        else {
            this.outerRadius = outerRadius;
        }
        double outerArea = Math.PI * Math.pow(outerRadius, 2);
        double holeArea = Math.PI * Math.pow(holeRadius, 2);
        this.area = outerArea - holeArea;
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
        double distanceFromCenter = Math.sqrt(Math.pow(center.getX() - x, 2) + Math.pow(center.getY() - y, 2));
        return distanceFromCenter <= outerRadius && distanceFromCenter >= holeRadius ;
    }

    @Override
    public String toString() {
        return "Donut{" +
                "id=" + id +
                ", x center=" + center.getX() +
                ", y center=" + center.getY() +
                ", holeRadius=" + holeRadius +
                ", outerRadius=" + outerRadius +
                ", area=" + area +
                '}';
    }
}
