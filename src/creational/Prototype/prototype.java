package creational.Prototype;

// Interface creational.Prototype
 interface Prototype {
    Prototype clone();
}

// Implementação concreta do creational.Prototype
class ConcretePrototype implements Prototype {
    private int value;

    public ConcretePrototype(int value) {
        this.value = value;
    }

    // Método clone para criar uma cópia do objeto
    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConcretePrototype with value: " + value;
    }
}

// Cliente que usa o padrão creational.Prototype
 class PrototypeDemo {
    public static void main(String[] args) {
        // Criando um objeto concreto
        ConcretePrototype prototype = new ConcretePrototype(42);
        System.out.println(prototype);  // Output: ConcretePrototype with value: 42

        // Clonando o objeto
        ConcretePrototype clonedObject = (ConcretePrototype) prototype.clone();
        System.out.println(clonedObject);  // Output: ConcretePrototype with value: 42

        // Modificando o objeto clonado
        clonedObject.setValue(100);
        System.out.println(clonedObject);  // Output: ConcretePrototype with value: 100
        System.out.println(prototype);  // Output: ConcretePrototype with value: 42 (objeto original permanece inalterado)
    }
}
