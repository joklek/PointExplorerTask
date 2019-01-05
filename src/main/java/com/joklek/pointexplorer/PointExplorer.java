package com.joklek.pointexplorer;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import com.joklek.pointexplorer.modules.ConsoleModule;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Shape;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PointExplorer {

    @Autowired
    private ShapeRepository repo;
    private Map<String, ConsoleModule> modulesWithHandle;

    public PointExplorer(List<ConsoleModule> modules) {
        this.modulesWithHandle = new HashMap<>();
        for(ConsoleModule module : modules) {
            if(modulesWithHandle.containsKey(module.getModuleHandle())) {
                throw new IllegalStateException("Incorrect configuration, multiple console modules with the same handle detected. Offender handle: " + module.getModuleHandle());
            }
            modulesWithHandle.put(module.getModuleHandle(), module);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String line = scanner.nextLine().trim();
            if(line.isEmpty()) {
                continue;
            }
            String[] splitResults = line.split("\\s+");
            String command = splitResults[0];

            if(splitResults.length == 2) {
                try {
                    double x = Double.parseDouble(splitResults[0]);
                    double y = Double.parseDouble(splitResults[1]);
                    calculateIncludedPoints(x, y);
                    continue;
                }
                catch (NumberFormatException ex){
                    // nothing, we will try other options
                }
            }

            if(command.equals("exit")) {
                return;
            }

            String[] arguments = Arrays.copyOfRange(splitResults, 1, splitResults.length);

            if(modulesWithHandle.containsKey(command)) {
                try {
                    String output = modulesWithHandle.get(command).parseCommand(arguments);
                    System.out.print(output);
                } catch (IncorrectModuleArgumentException e) {
                    System.out.println(String.format("Incorrect usage of command '%s' with error message '%s'", command, e.getMessage()));
                }
            }
            else {
                System.out.printf("Given command '%s' does not exist, for more information type \"help\"%n", line);
            }
        }
    }

    private void calculateIncludedPoints(double x, double y) {
        double areaSum = 0;
        for (Shape shape : repo.getAll()) {
            if (shape.doesIncludePoint(x, y)) {
                System.out.printf("%s %f %n", shape.getId(), shape.getArea());
                areaSum += shape.getArea();
            }
        }
        System.out.printf("The area sum of all shapes who have given point inside is %f %n", areaSum);
    }
}
