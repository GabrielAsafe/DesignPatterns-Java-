package creational.Factory;

// Produto
abstract class Pizza {
    public abstract void prepare();
    public abstract void bake();
    public abstract void cut();
}

// Produtos Concretos
class CheesePizza extends Pizza {
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

class PepperoniPizza extends Pizza {
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

// Criador (Fábrica)
abstract class PizzaStore {
    // Método fábrica (factory method)
    public abstract Pizza createPizza(String type);

    // Método que usa o produto
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        return pizza;
    }
}

// Criadores Concretos
class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new CheesePizza();
        } else if (type.equals("pepperoni")) {
            return new PepperoniPizza();
        } else {
            return null;
        }
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new CheesePizza();
        } else if (type.equals("pepperoni")) {
            return new PepperoniPizza();
        } else {
            return null;
        }
    }
}

// Cliente
class PizzaFactoryMethodExample {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ordered a Cheese Pizza from NY Store\n");

        pizza = chicagoStore.orderPizza("pepperoni");
        System.out.println("Ordered a Pepperoni Pizza from Chicago Store\n");
    }
}
