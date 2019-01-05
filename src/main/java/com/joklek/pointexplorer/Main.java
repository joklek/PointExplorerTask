package com.joklek.pointexplorer;

import com.joklek.pointexplorer.shape.Circle;
import com.joklek.pointexplorer.shape.Donut;
import com.joklek.pointexplorer.shape.Shape;
import com.joklek.pointexplorer.shape.Triangle;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Shape> shapes = new ArrayList<>();


        while(true) {
            String line = scanner.nextLine().trim();
            if(line.isEmpty()) {

            }
            String[] splitResults = line.split("\\s+");

            String command = splitResults[0];


            String[] shapeArguments = Arrays.copyOfRange(splitResults, 1, splitResults.length);

            if(command.equalsIgnoreCase("help")) {
                // TODO
                break;
            }
            else if(command.equalsIgnoreCase("exit")) {
                return;
            }
            else if(command.equalsIgnoreCase("circle")) {
                if(shapeArguments.length != 3) {
                    throw new IllegalStateException("Circle must contain 3 arguments in this order: center_x center_y radius");
                }
                // Todo handle parsing exceptions
                double x = Double.parseDouble(shapeArguments[0]);
                double y = Double.parseDouble(shapeArguments[1]);
                double radius = Double.parseDouble(shapeArguments[2]);

                Circle newShape = new Circle(UUID.randomUUID(), new Point(x, y), radius);
                shapes.add(newShape);
                System.out.printf("shape %s: circle with centre at (%f, %f) and radius %f %n", newShape.getId(), x, y, radius);
            }
            else if(command.equalsIgnoreCase("triangle")) {
                if(shapeArguments.length != 6) {
                    throw new IllegalStateException("Triangle must contain 6 arguments in this order: p1_x p1_y p2_x p2_y p3_x p3_y");
                }
                // Todo handle parsing exceptions
                double p0x = Double.parseDouble(shapeArguments[0]);
                double p0y = Double.parseDouble(shapeArguments[1]);
                double p1x = Double.parseDouble(shapeArguments[2]);
                double p1y = Double.parseDouble(shapeArguments[3]);
                double p2x = Double.parseDouble(shapeArguments[4]);
                double p2y = Double.parseDouble(shapeArguments[5]);

                Shape newShape = new Triangle(UUID.randomUUID(), new Point(p0x, p0y), new Point(p1x, p1y), new Point(p2x, p2y));
                shapes.add(newShape);

                System.out.printf("shape %s: triangle with points: p1(%f, %f), p2(%f, %f), p2(%f, %f)", newShape.getId(), p0x, p0y, p1x, p1y, p2x, p2y);
            }
            else if(command.equalsIgnoreCase("donut")) {
                if(shapeArguments.length != 4) {
                    throw new IllegalStateException("Donut must contain 4 arguments in this order: center_x center_y hole_radius outer_radius");
                }
                // Todo handle parsing exceptions
                double x = Double.parseDouble(shapeArguments[0]);
                double y = Double.parseDouble(shapeArguments[1]);
                double holeRadius = Double.parseDouble(shapeArguments[2]);
                double outerRadius = Double.parseDouble(shapeArguments[3]);

                Shape newShape = new Donut(UUID.randomUUID(), new Point(x, y), holeRadius, outerRadius);
                shapes.add(newShape);
                System.out.printf("shape %s: donut with centre at (%f, %f) and hole radius %f and outer radius %f %n", newShape.getId(), x, y, holeRadius, outerRadius);
            }
        }

    }
}
