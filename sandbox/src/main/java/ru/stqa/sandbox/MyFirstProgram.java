package ru.stqa.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        printHello("world");
        printHello("Natali");

        Square s = new Square(5);
        System.out.println("Area of square with a side " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(6, 4);
        System.out.println("Area of rectangle with sides " + r.a + " and " + r.b + " = " + r.area());

        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 4);
        System.out.println("Расстояние между точками (" + p1.x + ", " + p1.y + ") и  (" + p2.x + ", " + p2.y+ ") = " + p1.distance(p2));


    }

    public static void printHello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }


}