package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.shape.Point;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Shape;
import com.joklek.pointexplorer.shape.Triangle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TriangleModule implements ConsoleModule {

    @Autowired
    private ShapeRepository repo;

    private static final String HANDLE = "triangle";

    @Override
    public String parseCommand(String[] arguments) throws IllegalStateException {

        if(arguments.length != 6) {
            throw new IllegalStateException("Triangle must contain 6 arguments in this order: p1_x p1_y p2_x p2_y p3_x p3_y");
        }
        // Todo handle parsing exceptions

        double p0x, p0y;
        double p1x, p1y;
        double p2x, p2y;
        try {
            p0x = Double.parseDouble(arguments[0]);
            p0y = Double.parseDouble(arguments[1]);
            p1x = Double.parseDouble(arguments[2]);
            p1y = Double.parseDouble(arguments[3]);
            p2x = Double.parseDouble(arguments[4]);
            p2y = Double.parseDouble(arguments[5]);
        }
        catch (NumberFormatException ex) {
            throw new IllegalStateException();
            // TODO
        }

        Shape newShape = new Triangle(UUID.randomUUID(), new Point(p0x, p0y), new Point(p1x, p1y), new Point(p2x, p2y));
        repo.addNew(newShape);

        return String.format("shape %s: triangle with points: p1(%f, %f), p2(%f, %f), p2(%f, %f)%n", newShape.getId(), p0x, p0y, p1x, p1y, p2x, p2y);
    }

    @Override
    public String getModuleHandle() {
        return HANDLE;
    }

    @Override
    public String getHelpMessage() {
        return String.format("Usage should be: %s p1_x p1_y p2_x p2_y p3_x p3_y", HANDLE);
    }
}
