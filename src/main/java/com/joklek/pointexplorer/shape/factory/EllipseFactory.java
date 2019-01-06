package com.joklek.pointexplorer.shape.factory;

import com.joklek.pointexplorer.shape.Ellipse;
import com.joklek.pointexplorer.shape.Point;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class EllipseFactory {
    @NonNull
    public Ellipse createNew(@NonNull Point center, double radius1, double radius2) {
        return new Ellipse(UUID.randomUUID(), center, radius1, radius2);
    }

    @NonNull
    public Ellipse createNew(double x, double y, double radius1, double radius2) {
        return createNew(new Point(x, y), radius1, radius2);
    }
}
