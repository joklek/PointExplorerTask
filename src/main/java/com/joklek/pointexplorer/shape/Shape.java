package com.joklek.pointexplorer.shape;

import java.util.UUID;

public interface Shape {
    UUID getId();
    double getArea();
    boolean doesIncludePoint(double x, double y);
    default boolean doesIncludePoint(Point point) {
        return doesIncludePoint(point.getX(), point.getY());
    }
}
