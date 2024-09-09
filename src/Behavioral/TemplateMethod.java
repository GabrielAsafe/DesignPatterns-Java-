package Behavioral;

abstract class OrderProcessor {
    public final void processOrder() {
        selectProduct();
        makePayment();
        deliver();
        thankCustomer();
    }

    abstract void selectProduct();
    abstract void makePayment();
    abstract void deliver();

    void thankCustomer() {
        System.out.println("Obrigado por comprar conosco!");
    }
}

class OnlineOrderProcessor extends OrderProcessor {
    void selectProduct() {
        System.out.println("Selecionar produto online.");
    }

    void makePayment() {
        System.out.println("Pagamento online feito.");
    }

    void deliver() {
        System.out.println("Produto entregue por correio.");
    }
}

class StoreOrderProcessor extends OrderProcessor {
    void selectProduct() {
        System.out.println("Selecionar produto na loja.");
    }

    void makePayment() {
        System.out.println("Pagamento feito no caixa.");
    }

    void deliver() {
        System.out.println("Produto entregue ao cliente.");
    }
}

class TemplateMethodPatternExample {
    public static void main(String[] args) {
        OrderProcessor onlineOrder = new OnlineOrderProcessor();
        onlineOrder.processOrder();

        System.out.println();

        OrderProcessor storeOrder = new StoreOrderProcessor();
        storeOrder.processOrder();
    }
}
