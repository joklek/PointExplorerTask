package com.joklek.pointexplorer.shape;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DonutTest {

    @Test
    void shouldGetCorrectArea() {
        Point center = new Point(0, 0);
        int holeRadius = 3;
        int outerRadius = 4;
        Donut donut = new Donut(center, holeRadius, outerRadius);

        double expectedArea = Math.PI * Math.pow(outerRadius, 2) - Math.PI * Math.pow(holeRadius, 2);
        assertThat(donut.getArea(), is(expectedArea));
    }

    @Test
    void shouldThrowExceptionWhenHoleRadiusBiggerThanOuterRadius() {
        Point center = new Point(0, 0);
        int holeRadius = 4;
        int outerRadius = 3;
        Executable constructor = () -> new Donut(center, holeRadius, outerRadius);
        assertThrows(IllegalArgumentException.class, constructor);
    }

    @Test
    void shouldIncludePointInDonut() {
        Point center = new Point(0, 0);
        int holeRadius = 3;
        int outerRadius = 5;
        Donut donut = new Donut(center, holeRadius, outerRadius);

        assertTrue(donut.doesIncludePoint(0, 4));
    }

    @Test
    void shouldNotIncludePointInDonut() {
        Point center = new Point(0, 0);
        int holeRadius = 3;
        int outerRadius = 4;
        Donut donut = new Donut(center, holeRadius, outerRadius);

        assertFalse(donut.doesIncludePoint(0, 0));
    }

    @Test
    void shouldIncludePointOnInnerEdge() {
        Point center = new Point(0, 0);
        int holeRadius = 3;
        int outerRadius = 4;
        Donut circle = new Donut(center, holeRadius, outerRadius);

        assertTrue(circle.doesIncludePoint(3, 0));
    }

    @Test
    void shouldIncludePointOnOuterEdge() {
        Point center = new Point(0, 0);
        int holeRadius = 3;
        int outerRadius = 4;
        Donut circle = new Donut(center, holeRadius, outerRadius);

        assertTrue(circle.doesIncludePoint(4, 0));
    }

    private static Stream<Arguments> badRadius() {
        return Stream.of(
                arguments(0, 0),
                arguments(-1, 1),
                arguments(1, -1),
                arguments(-100, -100)
        );
    }
    @ParameterizedTest
    @MethodSource("badRadius")
    public void shouldThrowExceptionWhenBadRadius(int radius1, int radius2) {
        Point center = new Point(0, 0);
        Executable parseExecution = () -> new Donut(center, radius1, radius2);
        assertThrows(IllegalArgumentException.class, parseExecution);
    }
}
