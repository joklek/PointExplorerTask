package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Circle;
import com.joklek.pointexplorer.shape.factory.CircleFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("squid:S1659")
public class CircleModule implements ConsoleModule {

    @Autowired
    private ShapeRepository repo;

    @Autowired
    private CircleFactory factory;

    private static final String HANDLE = "circle";

    @Override
    public String parseCommand(String[] arguments) throws IncorrectModuleArgumentException {

        if(arguments.length != 3) {
            throw new IncorrectModuleArgumentException("Circle must contain 3 arguments in this order: center_x center_y radius");
        }

        double x, y;
        double radius;
        try {
            x = Double.parseDouble(arguments[0]);
            y = Double.parseDouble(arguments[1]);
            radius = Double.parseDouble(arguments[2]);
        }
        catch (NumberFormatException ex) {
            throw new IncorrectModuleArgumentException("Not all arguments were valid numbers");
        }

        Circle newShape = factory.createNew(x, y, radius);
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
