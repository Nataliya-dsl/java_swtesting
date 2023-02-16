package ru.stqa.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        printHello("world");
        printHello("Natali");

        double l = 5;
        System.out.println("Area of square with a side " + l + " = " + area(l));

        double a = 3;
        double b = 4;
        System.out.println("Area of rectangle with sides " + a + " and " + b + " = " + area(a,b));

    }

    public static void printHello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double l) {
        return l * l;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}