package com.joklek.pointexplorer.shape.factory;

import com.joklek.pointexplorer.shape.Donut;
import com.joklek.pointexplorer.shape.Point;
import com.joklek.pointexplorer.shape.Shape;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class DonutFactory {
    @NonNull
    public Donut createNew(@NonNull Point center, double holeRadius, double outerRadius) {
        return new Donut(UUID.randomUUID(), center, holeRadius, outerRadius);
    }

    @NonNull
    public Shape createNew(double x, double y, double holeRadius, double outerRadius) {
        return createNew(new Point(x, y), holeRadius, outerRadius);
    }
}
