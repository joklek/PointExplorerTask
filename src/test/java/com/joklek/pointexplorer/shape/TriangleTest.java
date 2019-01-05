package com.joklek.pointexplorer.shape;

import com.joklek.pointexplorer.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TriangleTest {

    private static Stream<Arguments> coordinatesAndAreas() {
        return Stream.of(
                arguments(0, 0,
                          0, 2,
                          2, 0,
                          2),
                arguments(2, 0,
                          0, 0,
                          0, 2,
                          2),
                arguments(0, 0,
                          2, 0,
                          0, 2,
                          2)
        );
    }

    @ParameterizedTest
    @MethodSource("coordinatesAndAreas")
    void shouldGetCorrectArea(double x0, double y0, double x1, double y1, double x2, double y2, double area) {
        Point p0 = new Point(x0, y0);
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Triangle triangle = new Triangle(p0, p1, p2);

        assertThat(triangle.getArea(), is(area));
    }

    private static Stream<Arguments> coordinatesPointAndItsInclusion() {
        return Stream.of(
                arguments(0, 0,
                          0, 2,
                          2, 0,
                          0.5, 0.5, true),
                arguments(2, 0,
                        0, 0,
                        0, 2,
                        0.5, 0.5, true),
                arguments(0, 0,
                        2, 0,
                        0, 2,
                        0.5, 0.5, true),
                arguments(0, 0,
                        0, 2,
                        2, 0,
                        1.5, 1.5, false),
                arguments(2, 0,
                        0, 0,
                        0, 2,
                        1.5, 1.5, false),
                arguments(0, 0,
                        2, 0,
                        0, 2,
                        1.5, 1.5, false)
        );
    }

    @ParameterizedTest
    @MethodSource("coordinatesPointAndItsInclusion")
    void shouldIncludePointInTriangle(double x0, double y0, double x1, double y1, double x2, double y2, double px, double py, boolean result) {
        Point p0 = new Point(x0, y0);
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Triangle triangle = new Triangle(p0, p1, p2);

        assertThat(triangle.doesIncludePoint(px, py), is(result));
    }
}
