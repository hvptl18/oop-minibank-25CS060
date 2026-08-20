abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private double radius;
    Circle(double radius) {
        this.radius = radius;
    }
    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double length, width;
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    @Override
    double area() {
        return length * width;
    }
}

class Triangle extends Shape {
    private double base, height;
    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    @Override
    double area() {
        return 0.5 * base * height;
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 7)
        };

        double totalArea = 0;
        double largestArea = 0;

        for (Shape s : shapes) {
            double a = s.area();
            System.out.println("Area: " + a);
            totalArea += a;
            if (a > largestArea) largestArea = a;
        }

        System.out.println("Total Area = " + totalArea);
        System.out.println("Largest Area = " + largestArea);
    }
}
