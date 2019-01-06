package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Shape;
import com.joklek.pointexplorer.shape.factory.DonutFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

public class DonutModule implements ConsoleModule {

    @Autowired
    private ShapeRepository repo;

    @Autowired
    private DonutFactory factory;

    private static final String HANDLE = "donut";

    @Override
    public String parseCommand(@NonNull String[] arguments) throws IncorrectModuleArgumentException {

        if(arguments.length != 4) {
            throw new IncorrectModuleArgumentException("Donut must contain 4 arguments in this order: center_x center_y hole_radius outer_radius");
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
            throw new IncorrectModuleArgumentException("Not all arguments were valid numbers");
        }
        if(holeRadius > outerRadius) {
            throw new IncorrectModuleArgumentException(String.format("Hole radius should be smaller than outer radius. %f is not smaller than %f", holeRadius, outerRadius));
        }

        Shape newShape = factory.createNew(x, y, holeRadius, outerRadius);
        repo.addNew(newShape);
        return String.format("shape %s: donut with centre at (%f, %f) and hole radius %f and outer radius %f %n", newShape.getId(), x, y, holeRadius, outerRadius);
    }

    @Override
    @NonNull
    public String getModuleHandle() {
        return HANDLE;
    }

    @Override
    @NonNull
    public String getHelpMessage() {
        return String.format("Usage should be: %s center_x center_y hole_radius outer_radius", HANDLE);
    }
}
