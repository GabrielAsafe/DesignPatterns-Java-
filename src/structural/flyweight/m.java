package structural.flyweight;

import java.util.HashMap;

import java.util.HashMap;

interface Shape {
    void draw(int x, int y, int radius);
}

class Circle implements Shape {
    private String color; // Intrinsic state

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw(int x, int y, int radius) {
        //System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius + "]");
    }
}

class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color: " + color);
        }
        return circle;
    }
}

 class FlyweightMemoryDemo {
    private static final String[] colors = { "Red", "Green", "Blue", "White", "Black" };

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        // Measure memory before object creation
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used before object creation: " + memoryBefore + " bytes");

        // Create a large number of flyweight objects
        for (int i = 0; i < 100000; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.draw(getRandomX(), getRandomY(), 100);
        }

        // Measure memory after object creation
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used after object creation: " + memoryAfter + " bytes");

        // Calculate the difference in memory usage
        long memoryConsumed = memoryAfter - memoryBefore;
        System.out.println("Memory consumed by 100,000 Circle objects: " + memoryConsumed + " bytes");
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
