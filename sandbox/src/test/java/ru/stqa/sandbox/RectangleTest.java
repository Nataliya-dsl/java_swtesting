package ru.stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTest {

    @Test
    public void testPointDistance1() {
        Point p1 = new Point(2,3);
        Point p2 = new Point(5,4);
        Assert.assertEquals(Math.round(p1.distance(p2)), 3);
    }

    @Test
    public void testPointDistance2() {
        Point p1 = new Point(2,4);
        Point p2 = new Point(4,6);
        Assert.assertEquals(Math.round(p1.distance(p2)), 3);
    }
}
