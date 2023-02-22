package ru.stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTest {

    @Test
    public void testArea() {
        Square s = new Square(5);
        Assert.assertEquals(s.area(), 25.0);
    }

    @Test
    public void testPointDistance() {
        Point p1 = new Point(2,3);
        Point p2 = new Point(5,4);
        Assert.assertEquals(Math.round(p1.distance(p2)), 3);
    }
}
