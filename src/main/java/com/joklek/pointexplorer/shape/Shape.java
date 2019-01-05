package com.joklek.pointexplorer.shape;

import com.joklek.pointexplorer.Point;

import java.util.UUID;

public interface Shape {
    UUID getId();
    double getArea();
    boolean doesIncludePoint(double x, double y);
    boolean doesIncludePoint(Point point);
}
