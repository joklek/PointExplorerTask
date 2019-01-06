package com.joklek.pointexplorer.repo;

import com.joklek.pointexplorer.shape.Shape;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Shape repository
 */
public interface ShapeRepository {
    /**
     * Adds shape to repository
     * @param shape shape to add
     */
    void addNew(@NonNull Shape shape);

    /**
     * Returns all shapes in repository
     * @return list of all shapes in repo
     */
    @NonNull
    List<Shape> getAll();
}
