package com.joklek.pointexplorer.shape.factory;

import com.joklek.pointexplorer.shape.Point;
import com.joklek.pointexplorer.shape.Triangle;

import java.util.UUID;

public class TriangleFactory {
    public Triangle createNew(Point p0, Point p1, Point p2) {
        return new Triangle(UUID.randomUUID(), p0, p1, p2);
    }
}
