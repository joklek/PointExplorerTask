package com.joklek.pointexplorer.shape;

import java.util.UUID;

public class Donut implements Shape {

    private UUID id;
    private final Point center;
    private final double holeRadius;
    private final double outerRadius;
    private final Circle hole;
    private final Circle donut;
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
        this.holeRadius = holeRadius;
        this.outerRadius = outerRadius;
        this.hole = new Circle(center, holeRadius);
        this.donut = new Circle(center, outerRadius);
        this.area = donut.getArea() - hole.getArea();
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
    public boolean doesIncludePoint(double x, double y) {
        return !hole.doesIncludePoint(x, y) && donut.doesIncludePoint(x, y);
    }
}
