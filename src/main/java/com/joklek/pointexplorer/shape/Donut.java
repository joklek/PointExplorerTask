package com.joklek.pointexplorer.shape;

import com.joklek.pointexplorer.Point;

import java.util.UUID;

public class Donut implements Shape {

    private UUID id;
    private final Point center;
    private final double radius1;
    private final double radius2;
    private final Circle hole;
    private final Circle donut;
    private final double area;

    public Donut(Point center, double radius1, double radius2) {
        this.center = center;
        this.radius1 = radius1;
        this.radius2 = radius2;

        double holeRadius;
        double donutRadius;
        if(radius1 > radius2) {
            donutRadius = radius1;
            holeRadius = radius2;
        }
        else {
            donutRadius = radius2;
            holeRadius = radius1;
        }

        this.hole = new Circle(center, holeRadius);
        this.donut = new Circle(center, donutRadius);
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
