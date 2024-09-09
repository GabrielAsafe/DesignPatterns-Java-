package Behavioral;

interface Shape {
    void accept(Visitor visitor);
}

class Circle implements Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Rectangle implements Shape {
    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

class AreaVisitor implements Visitor {
    public void visit(Circle circle) {
        double area = Math.PI * circle.radius * circle.radius;
        System.out.println("Área do círculo: " + area);
    }

    public void visit(Rectangle rectangle) {
        double area = rectangle.width * rectangle.height;
        System.out.println("Área do retângulo: " + area);
    }
}

class PerimeterVisitor implements Visitor {
    public void visit(Circle circle) {
        double perimeter = 2 * Math.PI * circle.radius;
        System.out.println("Perímetro do círculo: " + perimeter);
    }

    public void visit(Rectangle rectangle) {
        double perimeter = 2 * (rectangle.width + rectangle.height);
        System.out.println("Perímetro do retângulo: " + perimeter);
    }
}

class VisitorPatternExample {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        Visitor areaVisitor = new AreaVisitor();
        Visitor perimeterVisitor = new PerimeterVisitor();

        circle.accept(areaVisitor);
        rectangle.accept(areaVisitor);

        circle.accept(perimeterVisitor);
        rectangle.accept(perimeterVisitor);
    }
}

