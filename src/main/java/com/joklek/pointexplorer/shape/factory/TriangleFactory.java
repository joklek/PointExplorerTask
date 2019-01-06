package com.joklek.pointexplorer.shape.factory;

import com.joklek.pointexplorer.shape.Point;
import com.joklek.pointexplorer.shape.Triangle;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class TriangleFactory {
    @NonNull
    public Triangle createNew(@NonNull Point p0, @NonNull Point p1, @NonNull Point p2) {
        return new Triangle(UUID.randomUUID(), p0, p1, p2);
    }
}
