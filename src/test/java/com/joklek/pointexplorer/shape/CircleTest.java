package com.joklek.pointexplorer.shape;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
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

public class CircleTest {
    @Test
    void shouldGetCorrectArea() {
        Point center = new Point(0, 0);
        int radius = 3;
        Circle circle = new Circle(center, radius);

        assertThat(circle.getArea(), is(Math.PI * Math.pow(radius, 2)));
    }

    @Test
    void shouldIncludePointInCircle() {
        Point center = new Point(0, 0);
        int radius = 3;
        Circle circle = new Circle(center, radius);

        assertTrue(circle.doesIncludePoint(1, 1));
    }

    @Test
    void shouldNotIncludePointInCircle() {
        Point center = new Point(0, 0);
        int radius = 3;
        Circle circle = new Circle(center, radius);

        assertFalse(circle.doesIncludePoint(4, 4));
    }

    @Test
    void shouldIncludePointOnEdge() {
        Point center = new Point(0, 0);
        int radius = 3;
        Circle circle = new Circle(center, radius);

        assertTrue(circle.doesIncludePoint(3, 0));
    }

    private static Stream<Arguments> badRadius() {
        return Stream.of(
                arguments(0),
                arguments(-1),
                arguments(-100)
        );
    }
    @ParameterizedTest
    @MethodSource("badRadius")
    public void shouldThrowExceptionWhenBadRadius(int badRadius) {
        Point center = new Point(0, 0);
        Executable parseExecution = () -> new Circle(center, badRadius);
        assertThrows(IllegalArgumentException.class, parseExecution);
    }
}
