package com.joklek.pointexplorer.shape.factory;

import com.joklek.pointexplorer.shape.Circle;
import com.joklek.pointexplorer.shape.Point;

import java.util.UUID;

public class CircleFactory {
    public Circle createNew(Point center, double radius) {
        return new Circle(UUID.randomUUID(), center, radius);
    }

    public Circle createNew(double x, double y, double radius) {
        return createNew(new Point(x, y), radius);
    }
}
