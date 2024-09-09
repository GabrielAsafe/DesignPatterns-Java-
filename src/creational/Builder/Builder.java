package creational.Builder;

import java.util.ArrayList;
import java.util.List;

// Product
class Casa {
    private String paredes; //
    private String telhado;
    private String janelas;

    public void setParedes(String paredes) { this.paredes = paredes; }
    public void setTelhado(String telhado) { this.telhado = telhado; }
    public void setJanelas(String janelas) { this.janelas = janelas; }
}

// creational.Builder interface
interface CasaBuilder {
    void construirParedes();
    void construirTelhado();
    void construirJanelas();
    Casa getCasa();
}

// ConcreteBuilder vai criar o produto com base na sua interface
class CasaDeMadeiraBuilder implements CasaBuilder {
    private Casa casa;

    public CasaDeMadeiraBuilder() {//construtor
        this.casa = new Casa();
    }

    public void construirParedes() {// implementação da interface com base no produto
        casa.setParedes("Paredes de madeira");
    }

    public void construirTelhado() {
        casa.setTelhado("Telhado de madeira");
    }

    public void construirJanelas() {
        casa.setJanelas("Janelas de vidro");
    }

    public Casa getCasa() { // retorna o objeto criado para permitir o acesso posterior
        return this.casa;
    }
}

// Director vai instanciar a interface pois quando formos criar um objeto daquela classe não sabemos se ela vai ser de madeira ou de tijolo, mas como ambas implementam a mesma interface, os métodos são os mesmos
class Diretor {
    private CasaBuilder builder;

    public Diretor(CasaBuilder builder) {
        this.builder = builder;
    }

    public void construirCasa() {
        builder.construirParedes();
        builder.construirTelhado();
        builder.construirJanelas();
    }

    public Casa getCasa() {
        return builder.getCasa();
    }
}

// Uso
class Cliente {
//    public static void main(String[] args) {
//        //a interface define quais partes um objeto deveria ter, sendo elas, partes do produto
//        CasaBuilder builder = new CasaDeMadeiraBuilder();//constroes um objeto do tipo da interface pois permite que, se mudar o tipo de objeto, não precisa reimplementar os métodos.
//        Diretor diretor = new Diretor(builder);//o diretor vai chamar chamar a sequencia de passinhos, e a sequencia de passisnhos vai ser do tipo casaDeMadeira
//        diretor.construirCasa();//chamou os passinhos aqui
//        Casa casa = diretor.getCasa();
//
//        System.out.println("Casa construída: " + casa);
//    }



    //em geral, para cada novo objeto Casa que você deseja construir, você precisará criar um novo creational.Builder e um novo Diretor, especialmente se cada objeto for único ou diferente dos outros.

    public static void main(String[] args) {
        // Criação de uma lista para armazenar as casas construídas
        List<Casa> casas = new ArrayList<>();

        // Loop para construir múltiplas casas
        for (int i = 0; i < 10; i++) {
            CasaBuilder builder = new CasaDeMadeiraBuilder();
            Diretor diretor = new Diretor(builder);

            // Construir a casa
            diretor.construirCasa();
            Casa casa = diretor.getCasa();

            // Adicionar a casa à lista
            casas.add(casa);
        }

        // Agora a lista 'casas' contém 10 objetos do tipo Casa
        for (Casa c : casas) {
            System.out.println("Casa construída: " + c);
        }
    }
}