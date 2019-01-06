package com.joklek.pointexplorer.shape.factory;

import com.joklek.pointexplorer.shape.Circle;
import com.joklek.pointexplorer.shape.Point;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class CircleFactory {
    @NonNull
    public Circle createNew(@NonNull Point center, double radius) {
        return new Circle(UUID.randomUUID(), center, radius);
    }

    @NonNull
    public Circle createNew(double x, double y, double radius) {
        return createNew(new Point(x, y), radius);
    }
}
