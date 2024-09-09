package Behavioral;

class Context {
    // Context could hold global information, but for simplicity, it's empty.
}

 interface Expression {
    int interpret(Context context);
}


class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret(Context context) {
        return number;
    }
}

class AddExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AddExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(Context context) {
        return leftExpression.interpret(context) + rightExpression.interpret(context);
    }
}

class SubtractExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public SubtractExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(Context context) {
        return leftExpression.interpret(context) - rightExpression.interpret(context);
    }
}


class OClient {
    public static void main(String[] args) {
        Context context = new Context();

        // (5 + 10) - (2 + 3)
        Expression expression = new SubtractExpression(
                new AddExpression(new NumberExpression(5), new NumberExpression(10)),
                new AddExpression(new NumberExpression(2), new NumberExpression(3))
        );

        int result = expression.interpret(context);
        System.out.println("Resultado: " + result);
    }
}
