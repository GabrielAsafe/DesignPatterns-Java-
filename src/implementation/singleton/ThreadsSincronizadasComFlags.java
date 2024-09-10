package implementation.singleton;

// Classe que representa o recurso compartilhado entre o produtor e o consumidor
class CubbyHole {
    private int content;         // Armazena o conteúdo que o produtor produz e o consumidor consome
    private boolean isAvaliable; // Indica se há conteúdo disponível para o consumidor

    // Método sincronizado para o consumidor pegar o conteúdo
    public synchronized int getContent() {
        // Enquanto não houver conteúdo disponível, o consumidor espera
        while (!isAvaliable) {
            try {
                wait(); // Libera o bloqueio e espera até ser notificado
            } catch (InterruptedException e) {
                // Em caso de interrupção, preserva o status de interrupção e lança uma exceção
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        // Marca que o conteúdo não está mais disponível, já que o consumidor o pegou
        isAvaliable = false;
        notifyAll(); // Notifica o produtor que a vaga está disponível
        return content; // Retorna o conteúdo consumido
    }

    // Método sincronizado para o produtor definir o conteúdo
    public synchronized void setContent(int content) {
        // Enquanto já houver conteúdo disponível (ou seja, não foi consumido ainda), o produtor espera
        while (isAvaliable) {
            try {
                wait(); // Libera o bloqueio e espera até ser notificado
            } catch (InterruptedException e) {
                // Em caso de interrupção, preserva o status de interrupção e lança uma exceção
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        // Define o novo conteúdo produzido
        this.content = content;
        // Marca que há conteúdo disponível para ser consumido
        isAvaliable = true;
        notifyAll(); // Notifica o consumidor que há um novo conteúdo disponível
    }
}

// Classe que representa o produtor
class Producer extends Thread {
    private CubbyHole cubbyhole; // Referência ao recurso compartilhado

    // Construtor que recebe o recurso compartilhado
    public Producer(CubbyHole c) {
        cubbyhole = c;
    }

    @Override
    public void run() {
        // O produtor cria 10 itens
        for (int i = 0; i < 10; i++) {
            System.out.println("Producer set the number " + i);
            cubbyhole.setContent(i); // Produz um item e o coloca no CubbyHole

        }
    }
}

// Classe que representa o consumidor
class Consumer extends Thread {
    private CubbyHole cubbyhole; // Referência ao recurso compartilhado

    // Construtor que recebe o recurso compartilhado
    public Consumer(CubbyHole c) {
        cubbyhole = c;
    }

    @Override
    public void run() {
        // O consumidor consome 10 itens
        for (int i = 0; i < 10; i++) {
            // Consome um item do CubbyHole
            System.out.println("Consumer got the number " + cubbyhole.getContent());

        }
    }
}

// Classe cliente que inicia o processo de produção e consumo
class Client {
    public static void main(String[] args) {
        CubbyHole c = new CubbyHole(); // Cria o recurso compartilhado
        Producer p1 = new Producer(c); // Cria o produtor
        Consumer c1 = new Consumer(c); // Cria o consumidor

        p1.start(); // Inicia a execução do produtor
        c1.start(); // Inicia a execução do consumidor
    }
}
