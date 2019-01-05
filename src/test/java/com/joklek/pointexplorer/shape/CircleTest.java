package com.joklek.pointexplorer.shape;

import com.joklek.pointexplorer.Point;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
