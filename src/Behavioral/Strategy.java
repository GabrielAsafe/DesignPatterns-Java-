package Behavioral;

interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Pago " + amount + " usando cartão de crédito.");
    }
}

class PaypalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Pago " + amount + " usando PayPal.");
    }
}

class BankTransferPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Pago " + amount + " usando transferência bancária.");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

class StrategyPatternExample {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100);

        cart.setPaymentStrategy(new PaypalPayment());
        cart.checkout(200);

        cart.setPaymentStrategy(new BankTransferPayment());
        cart.checkout(300);
    }
}
