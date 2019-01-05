package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.shape.Point;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CircleModule implements ConsoleModule {

    @Autowired
    private ShapeRepository repo;

    private static final String HANDLE = "circle";

    @Override
    public String parseCommand(String[] arguments) throws IllegalStateException {

        if(arguments.length != 3) {
            throw new IllegalStateException("Circle must contain 3 arguments in this order: center_x center_y radius");
        }
        // Todo handle parsing exceptions
        double x = Double.parseDouble(arguments[0]);
        double y = Double.parseDouble(arguments[1]);
        double radius = Double.parseDouble(arguments[2]);

        Circle newShape = new Circle(UUID.randomUUID(), new Point(x, y), radius);
        repo.addNew(newShape);
        return String.format("shape %s: circle with centre at (%f, %f) and radius %f %n", newShape.getId(), x, y, radius);
    }

    @Override
    public String getModuleHandle() {
        return HANDLE;
    }

    @Override
    public String getHelpMessage() {
        return String.format("Usage should be: %s center_x center_y radius", HANDLE);
    }
}
