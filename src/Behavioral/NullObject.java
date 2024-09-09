package Behavioral;

interface Logger {
    void log(String message);
}

class ConsoleLogger implements Logger {
    public void log(String message) {
        System.out.println("Log para o console: " + message);
    }
}

class NullLogger implements Logger {
    public void log(String message) {
        System.out.println("passei o null e fds hehe");
    }
}

class Application {
    private Logger logger;

    Application(Logger logger) {
        this.logger = logger != null ? logger : new NullLogger();
    }

    void doSomething() {
        logger.log("Aplicação começou a fazer algo.");
        // Mais código aqui...
        logger.log("Aplicação terminou de fazer algo.");
    }
}

class NullObjectPatternExample {
    public static void main(String[] args) {
        Application appWithLogger = new Application(new ConsoleLogger());
        appWithLogger.doSomething();

        Application appWithoutLogger = new Application(null);
        appWithoutLogger.doSomething();
    }
}
