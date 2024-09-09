package creational.Factory;

// Produto
abstract class Pizza2 {
    public abstract void prepare();
    public abstract void bake();
    public abstract void cut();
}

// Produtos Concretos
class CheesePizza2 extends Pizza2 {
    public void prepare() {
        System.out.println("Preparing Cheese Pizza");
    }
    public void bake() {
        System.out.println("Baking Cheese Pizza");
    }
    public void cut() {
        System.out.println("Cutting Cheese Pizza");
    }
}

class PepperoniPizza2 extends Pizza2 {
    public void prepare() {
        System.out.println("Preparing Pepperoni Pizza");
    }
    public void bake() {
        System.out.println("Baking Pepperoni Pizza");
    }
    public void cut() {
        System.out.println("Cutting Pepperoni Pizza");
    }
}

// Simple creational.Factory é o encapsulamento do construtor em um método separado
class PizzaFactory2 {
    public static Pizza2 createPizza(String type) {
        Pizza2 pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza2();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza2();
        }
        return pizza;
    }
}
// Cliente
class PizzaStore2 {
    public static void main(String[] args) {
        Pizza2 pizza = PizzaFactory2.createPizza("cheese");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
    }
}
