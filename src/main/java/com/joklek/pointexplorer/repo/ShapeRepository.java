package com.joklek.pointexplorer.repo;

import com.joklek.pointexplorer.shape.Shape;

import java.util.List;

public interface ShapeRepository {
    void addNew(Shape shape);
    List<Shape> getAll();
}
