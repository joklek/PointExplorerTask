package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Ellipse;
import com.joklek.pointexplorer.shape.factory.EllipseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

@SuppressWarnings("squid:S1659")
public class EllipseModule implements ConsoleModule{

    @Autowired
    private ShapeRepository repo;

    @Autowired
    private EllipseFactory factory;

    private static final String HANDLE = "ellipse";

    @Override
    @NonNull
    public String parseCommand(@NonNull String[] arguments) throws IncorrectModuleArgumentException {

        if(arguments.length != 4) {
            throw new IncorrectModuleArgumentException("Ellipse must contain 3 arguments in this order: center_x center_y radius1 radius2");
        }

        double x, y;
        double radius1, radius2;
        try {
            x = Double.parseDouble(arguments[0]);
            y = Double.parseDouble(arguments[1]);
            radius1 = Double.parseDouble(arguments[2]);
            radius2 = Double.parseDouble(arguments[3]);
        }
        catch (NumberFormatException ex) {
            throw new IncorrectModuleArgumentException("Not all arguments were valid numbers");
        }
        if(radius1 <= 0) {
            throw new IncorrectModuleArgumentException("Hole radius must be positive, but is " + radius1);
        }
        if(radius2 <= 0) {
            throw new IncorrectModuleArgumentException("Hole radius must be positive, but is " + radius2);
        }

        Ellipse newShape = factory.createNew(x, y, radius1, radius2);
        repo.addNew(newShape);
        return String.format("shape %s: ellipse with centre at (%f, %f) and radius %f and %f %n", newShape.getId(), x, y, radius1, radius2);
    }

    @Override
    @NonNull
    public String getModuleHandle() {
        return HANDLE;
    }

    @Override
    @NonNull
    public String getHelpMessage() {
        return String.format("Usage should be: %s center_x center_y radius1 radius2", HANDLE);
    }
}
