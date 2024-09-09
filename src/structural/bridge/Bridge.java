package structural.bridge;

// Implementor interface
 interface DrawAPI {
    void drawCircle(int radius, int x, int y);
 }

// Concrete Implementor 1
 class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", y: " + y + "]");
    }
}

// Concrete Implementor 2
 class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", y: " + y + "]");
    }
}

// Abstraction
 abstract class Shape {
    protected DrawAPI drawAPI; //essa merda é o que faz a ligação a outra interface e está aqui pq o design pattern é assim mesmo. tem que ter a ref para a outra interface

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

// Refined Abstraction (Circle)
 class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}

// Client
 class Client {
    public static void main(String[] args) {




        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();   // Output: Drawing Circle[ color: red, radius: 10, x: 100, y: 100]
        greenCircle.draw(); // Output: Drawing Circle[ color: green, radius: 10, x: 100, y: 100]
    }
}
