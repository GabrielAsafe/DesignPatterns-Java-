package Behavioral;

// Handler Interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String issue);
}


// ConcreteHandler 1 - Suporte de Nível 1
 class Level1Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("Simples")) {
            System.out.println("Nível 1 resolveu o problema: " + issue);
        } else {
            System.out.println("Nível 1 não pode resolver o problema. Passando para o próximo nível...");
            if (nextHandler != null) {
                nextHandler.handleRequest(issue);
            }
        }
    }
}

// ConcreteHandler 2 - Suporte de Nível 2
 class Level2Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("Intermediário")) {
            System.out.println("Nível 2 resolveu o problema: " + issue);
        } else {
            System.out.println("Nível 2 não pode resolver o problema. Passando para o próximo nível...");
            if (nextHandler != null) {
                nextHandler.handleRequest(issue);
            }
        }
    }
}

// ConcreteHandler 3 - Suporte Especializado
 class ExpertSupport extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("Complexo")) {
            System.out.println("Suporte Especializado resolveu o problema: " + issue);
        } else {
            System.out.println("Problema " + issue + " não pode ser resolvido.");
        }
    }
}


class Client {
    public static void main(String[] args) {
        // Criando os handlers
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler expert = new ExpertSupport();

        // Configurando a cadeia de responsabilidade
        level1.setNextHandler(level2);
        level2.setNextHandler(expert);

        // Fazendo requisições
        System.out.println("Tentando resolver um problema Simples:");
        level1.handleRequest("Simples");

        System.out.println("\nTentando resolver um problema Intermediário:");
        level1.handleRequest("Intermediário");

        System.out.println("\nTentando resolver um problema Complexo:");
        level1.handleRequest("Complexo");

        System.out.println("\nTentando resolver um problema Desconhecido:");
        level1.handleRequest("Desconhecido");
    }
}
