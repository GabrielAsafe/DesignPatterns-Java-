package Behavioral;


/**
 * O padrão State permite que um objeto altere seu comportamento quando seu estado interno muda. O objeto parecerá mudar de classe.  Ele carrega o novo estado cada vez que faz um next
 */

interface OrderState {
    void next(Order order);
    void prev(Order order);
    void printStatus();
}

class NewOrder implements OrderState {
    public void next(Order order) {
        order.setState(new Processing());
    }

    public void prev(Order order) {
        System.out.println("Este pedido está no estado inicial.");
    }

    public void printStatus() {
        System.out.println("Pedido em estado Novo.");
    }
}

class Processing implements OrderState {
    public void next(Order order) {
        order.setState(new Shipped());
    }

    public void prev(Order order) {
        order.setState(new NewOrder());
    }

    public void printStatus() {
        System.out.println("Pedido está sendo processado.");
    }
}

class Shipped implements OrderState {
    public void next(Order order) {
        order.setState(new Delivered());
    }

    public void prev(Order order) {
        order.setState(new Processing());
    }

    public void printStatus() {
        System.out.println("Pedido foi enviado.");
    }
}

class Delivered implements OrderState {
    public void next(Order order) {
        System.out.println("Este pedido já foi entregue.");
    }

    public void prev(Order order) {
        order.setState(new Shipped());
    }

    public void printStatus() {
        System.out.println("Pedido entregue.");
    }
}

class Order {
    private OrderState state = new NewOrder();

    public void setState(OrderState state) {
        this.state = state;
    }

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        state.printStatus();
    }
}

class StatePatternExample {
    public static void main(String[] args) {
        Order order = new Order();

        order.printStatus();
        order.nextState();

        order.printStatus();
        order.nextState();

        order.printStatus();
        order.nextState();

        order.printStatus();
        order.previousState();

        order.printStatus();
    }
}
