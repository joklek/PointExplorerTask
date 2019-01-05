package com.joklek.pointexplorer.repo;

import com.joklek.pointexplorer.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class DefaultShapeRepo implements ShapeRepository {

    private List<Shape> shapes;

    public DefaultShapeRepo(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public DefaultShapeRepo() {
        this.shapes = new ArrayList<>();
    }

    @Override
    public void addNew(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public List<Shape> getAll() {
        return shapes;
    }
}
