package com.joklek.pointexplorer.shape;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

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
    
}
