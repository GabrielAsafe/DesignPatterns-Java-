package implementation.singleton;

import java.util.Date;

// Interface para definir o comportamento de um consumidor
interface ConsumerDef {
    // Método para receber um produto
    Product receberProduto();

    // Método para verificar o tipo do produto
    void verificarType();

    // Método para processar os dados do produto
    void processarDados();
}

// Interface para definir o comportamento de um produtor
interface ProducerDef {
    // Método para criar um produto
    Product createProduct();

    // Método para enviar um produto
    Product sendProduct();
}

// Interface que representa um Produto
interface Product {
    // Método que define o tipo do produto
    String defineType();
}

// Classe que contém as especificações de um produto
class Especificacoes {
    String nome; // Nome do produto
    int peso; // Peso do produto
    Date validade; // Data de validade do produto

    // Construtor da classe Especificacoes
    public Especificacoes(String nome, int peso, Date validade) {
        this.nome = nome;
        this.peso = peso;
        this.validade = validade;
    }

    // Métodos getter e setter para os atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }
}

// Classe abstrata que implementa a interface Product
abstract class Produto implements Product {
    @Override
    public String defineType() {
        // Define o tipo do produto (pode ser sobrescrito pelas subclasses)
        return null;
    }
}

// Classe que representa um pacote de doces, que é um tipo de produto
class PacoteDeDoces extends Produto {

    private Especificacoes especificacoes; // Especificações do pacote de doces
    private String tipo; // Tipo do produto

    // Construtor da classe PacoteDeDoces
    public PacoteDeDoces(Especificacoes especificacoes) {
        this.especificacoes = especificacoes;
        this.tipo = defineType(); // Define o tipo do produto
    }

    @Override
    public String defineType() {
        // Define que este produto é do tipo alimentício
        return "produtoAlimenticio";
    }

    // Método getter para o tipo do produto
    public String getTipo() {
        return tipo;
    }
}

// Classe que representa um produtor de bens alimentícios
// Estende a classe Thread para permitir que o produtor seja executado em uma thread separada
class ProdutorDeBensAlimenticios extends Thread implements ProducerDef {
    private boolean podeProduzir; // Flag para controlar quando o produtor pode produzir

    @Override
    public Product createProduct() {
        // Cria as especificações de um novo produto (pacote de doces)
        Especificacoes especificacoes = new Especificacoes("Açúcar do povo", 1, new Date(12, 11, 2020));
        Product produto = new PacoteDeDoces(especificacoes);
        return produto;
    }

    @Override
    public void run() {
        // Inicia o processo de envio do produto quando a thread é iniciada
        sendProduct();
    }

    @Override
    public synchronized Produto sendProduct() {
        while (podeProduzir) {
            try {
                wait(); // Libera o bloqueio e espera até ser notificado
            } catch (InterruptedException e) {
                // Em caso de interrupção, preserva o status de interrupção e lança uma exceção
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        // Define que o produto pode ser produzido
        podeProduzir = true;
        notifyAll(); // Notifica o consumidor que há um novo conteúdo disponível

        return (Produto) createProduct(); // Retorna o produto criado
    }
}

// Classe que representa um consumidor de bens alimentícios
// Estende a classe Thread para permitir que o consumidor seja executado em uma thread separada
class ConsumidorDeBensAlimenticios extends Thread implements ConsumerDef {
    Produto produto; // Produto que o consumidor vai consumir
    private boolean podeReceber; // Flag para controlar quando o consumidor pode receber

    @Override
    public void run() {
        // Inicia o processo de recebimento do produto quando a thread é iniciada
        receberProduto();
    }

    @Override
    public synchronized Product receberProduto() {
        // Enquanto não houver conteúdo disponível, o consumidor espera
        while (!podeReceber) {
            try {
                wait(); // Libera o bloqueio e espera até ser notificado
            } catch (InterruptedException e) {
                // Em caso de interrupção, preserva o status de interrupção e lança uma exceção
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        // Marca que o conteúdo não está mais disponível, já que o consumidor o pegou
        podeReceber = false;
        notifyAll(); // Notifica o produtor que a vaga está disponível
        return produto; // Retorna o conteúdo consumido
    }

    @Override
    public void verificarType() {
        // Verifica que são bens alimentícios antes de receber (implementação futura)
    }

    @Override
    public void processarDados() {
        // Verifica se a data de validade já passou (implementação futura)
    }
}

// Classe principal que contém o método main
class ClienMCAP {
    public static void main(String[] args) {

        // Cria instâncias do produtor e do consumidor
        ProdutorDeBensAlimenticios produtorDeBensAlimenticios = new ProdutorDeBensAlimenticios();
        ConsumidorDeBensAlimenticios consumidorDeBensAlimenticios = new ConsumidorDeBensAlimenticios();

        // Inicia as threads do produtor e do consumidor
        produtorDeBensAlimenticios.start();
        consumidorDeBensAlimenticios.start();
    }
}
