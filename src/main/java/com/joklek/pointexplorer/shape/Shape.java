package com.joklek.pointexplorer.shape;

import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * Interface for shape
 */
public interface Shape {
    /**
     * Gets unique id of a shape
     * @return shape id
     */
    @NonNull
    UUID getId();

    /**
     * Gets area of shape
     * @return
     */
    double getArea();

    /**
     * Checks if given coordinates are in shape
     * @param x x of point
     * @param y y of point
     * @return returns true if given point is in shape
     */
    boolean doesIncludePoint(double x, double y);

    /**
     * Checks if given point is in shape
     * @param point point to check
     * @return returns true if given point is in shape
     */
    default boolean doesIncludePoint(@NonNull Point point) {
        return doesIncludePoint(point.getX(), point.getY());
    }
}
