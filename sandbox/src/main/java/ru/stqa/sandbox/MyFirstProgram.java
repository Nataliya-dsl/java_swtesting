package ru.stqa.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        printHello("world");
        printHello("Natali");

        Square s = new Square(5);
        System.out.println("Area of square with a side " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(6, 4);
        System.out.println("Area of rectangle with sides " + r.a + " and " + r.b + " = " + r.area());

    }

    public static void printHello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}