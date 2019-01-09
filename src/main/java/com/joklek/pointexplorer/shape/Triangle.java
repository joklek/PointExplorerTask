package com.joklek.pointexplorer.shape;

import org.springframework.lang.NonNull;

import java.util.UUID;

@SuppressWarnings("squid:S1659")
public class Triangle implements Shape {

    private UUID id;
    private final Point p0, p1, p2;
    private final double area;
    // this sign field is used for point inclusion calculations
    private final double sign;

    public Triangle(UUID id, Point p0, Point p1, Point p2) {
        this(p0, p1, p2);
        this.id = id;
    }

    Triangle(Point p0, Point p1, Point p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        // area = | (Ax(By-Cy) + Bx(Cy-Ay) + Cx(Ay-By)) /2 |
        double tempArea = 0.5 * ((p1.getX()-p0.getX())*(p2.getY()-p0.getY()) - (p2.getX()-p0.getX())*(p1.getY()-p0.getY()));
        this.sign = tempArea < 0 ? -1 : 1;
        this.area = Math.abs(tempArea);
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
        double s = (p0.getY() * p2.getX() - p0.getX() * p2.getY() + (p2.getY() - p0.getY()) * x + (p0.getX() - p2.getX()) * y) * sign;
        double t = (p0.getX() * p1.getY() - p0.getY() * p1.getX() + (p0.getY() - p1.getY()) * x + (p1.getX() - p0.getX()) * y) * sign;

        return s >= 0 && t >= 0 && (s + t) <= 2 * area;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "id=" + id +
                ", x p0=" + p0.getX() +
                ", y p0=" + p0.getY() +
                ", x p1=" + p1.getX() +
                ", y p1=" + p1.getY() +
                ", x p2=" + p2.getX() +
                ", y p2=" + p2.getY() +
                ", area=" + area +
                '}';
    }
}
