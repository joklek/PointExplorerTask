package com.joklek.pointexplorer.repo;

import com.joklek.pointexplorer.shape.Shape;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DefaultShapeRepo implements ShapeRepository {

    private List<Shape> shapes;

    public DefaultShapeRepo(@NonNull List<Shape> shapes) {
        this.shapes = shapes;
    }

    public DefaultShapeRepo() {
        this.shapes = new ArrayList<>();
    }

    @Override
    public void addNew(@NonNull Shape shape) {
        shapes.add(shape);
    }

    @Override
    @NonNull
    public List<Shape> getAll() {
        return shapes;
    }
}
