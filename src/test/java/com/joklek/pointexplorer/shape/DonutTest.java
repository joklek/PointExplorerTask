package com.joklek.pointexplorer.shape;

import com.joklek.pointexplorer.Point;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DonutTest {

    @Test
    void shouldGetCorrectArea() {
        Point center = new Point(0, 0);
        int radius1 = 3;
        int radius2 = 4;
        Donut donut = new Donut(center, radius1, radius2);

        double expectedArea = Math.PI * Math.pow(radius2, 2) - Math.PI * Math.pow(radius1, 2);
        assertThat(donut.getArea(), is(expectedArea));
    }

    @Test
    void shouldGetCorrectAreaForInvertedRadius() {
        Point center = new Point(0, 0);
        int radius1 = 4;
        int radius2 = 3;
        Donut donut = new Donut(center, radius1, radius2);

        double expectedArea = Math.PI * Math.pow(radius1, 2) - Math.PI * Math.pow(radius2, 2);
        assertThat(donut.getArea(), is(expectedArea));
    }

    @Test
    void shouldIncludePointInDonut() {
        Point center = new Point(0, 0);
        int radius1 = 5;
        int radius2 = 3;
        Donut donut = new Donut(center, radius1, radius2);

        assertTrue(donut.doesIncludePoint(0, 4));
    }

    @Test
    void shouldNotIncludePointInDonut() {
        Point center = new Point(0, 0);
        int radius1 = 4;
        int radius2 = 3;
        Donut donut = new Donut(center, radius1, radius2);

        assertFalse(donut.doesIncludePoint(0, 0));
    }
    
}
