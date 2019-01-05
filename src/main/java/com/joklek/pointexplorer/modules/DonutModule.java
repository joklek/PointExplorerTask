package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.shape.Point;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Donut;
import com.joklek.pointexplorer.shape.Shape;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class DonutModule implements ConsoleModule {

    @Autowired
    private ShapeRepository repo;

    private static final String HANDLE = "donut";

    @Override
    public String parseCommand(String[] arguments) throws IllegalStateException {

        if(arguments.length != 4) {
            throw new IllegalStateException("Donut must contain 4 arguments in this order: center_x center_y hole_radius outer_radius");
        }

        double x;
        double y;
        double holeRadius;
        double outerRadius;
        try {
            x = Double.parseDouble(arguments[0]);
            y = Double.parseDouble(arguments[1]);
            holeRadius = Double.parseDouble(arguments[2]);
            outerRadius = Double.parseDouble(arguments[3]);
        }
        catch (NumberFormatException ex) {
            throw new IllegalStateException();
            // TODO
        }

        Shape newShape = new Donut(UUID.randomUUID(), new Point(x, y), holeRadius, outerRadius);
        repo.addNew(newShape);
        return String.format("shape %s: donut with centre at (%f, %f) and hole radius %f and outer radius %f %n", newShape.getId(), x, y, holeRadius, outerRadius);
    }

    @Override
    public String getModuleHandle() {
        return HANDLE;
    }

    @Override
    public String getHelpMessage() {
        return String.format("Usage should be: %s center_x center_y hole_radius outer_radius", HANDLE);
    }
}
