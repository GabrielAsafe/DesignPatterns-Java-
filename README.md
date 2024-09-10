[Design patterns](https://refactoring.guru/design-patterns)

---

# Padrões de Projeto (Design Patterns)

Este repositório contém exemplos e explicações sobre diversos padrões de projeto (Design Patterns), organizados por categorias: Creational, Structural, e Behavioral. Os padrões de projeto são soluções recorrentes para problemas comuns encontrados no desenvolvimento de software orientado a objetos.

## Padrões de Criação (Creational Design Patterns)

Os padrões de criação tratam da instância de objetos, oferecendo diferentes formas de criar objetos, dependendo do contexto.

### Builder

O `Builder` é um padrão de projeto criacional utilizado em situações onde um objeto precisa ser construído de maneira controlada e complexa, permitindo criar diferentes representações de um objeto passo a passo. Esse padrão é particularmente útil quando a criação de um objeto envolve várias etapas ou quando o objeto possui uma grande quantidade de parâmetros opcionais.

#### Características

- **Separação de Construção e Representação:** O `Builder` separa a construção do objeto (o processo passo a passo) da sua representação final. Isso facilita a criação de diferentes representações do objeto usando o mesmo processo de construção.
- **Construção Passo a Passo:** Permite que o objeto seja construído em etapas, adicionando cada parte ao objeto de forma sequencial. Isso é útil para objetos complexos que possuem muitas partes ou propriedades que podem ser configuradas de maneira diferente.
- **Produto Imutável:** O objeto final construído geralmente é imutável, garantindo que ele não será alterado após sua criação.

#### Composição

- **Diretor:** Usa o `Builder` (instância) e sabe a sequência de passos para a construção de um produto (objeto final). Ele possui uma implementação do tipo do produto para referenciá-lo posteriormente.
- **Builder:** Interface ou classe abstrata que define métodos para a construção do produto, geralmente incluindo `gets` e um método `build`.
- **ConcreteBuilder:** Implementa a interface `Builder`.
- **Produto:** Classe final que representa o objeto construído.

#### Considerações

- O padrão `Builder` é ideal para situações em que objetos complexos com múltiplas configurações precisam ser criados.

### Simple Factory

O `Simple Factory` é um padrão de projeto criacional que encapsula a lógica de criação de objetos dentro de uma única classe. Ao contrário de padrões como o `Factory Method` ou o `Abstract Factory`, o `Simple Factory` não é um dos "padrões de projeto" formais descritos no livro *Design Patterns: Elements of Reusable Object-Oriented Software*. No entanto, ele é uma prática comum e amplamente usada na programação orientada a objetos.

#### Características

- **Factory:** A classe que contém o método para criar objetos. Este método geralmente é estático, o que facilita a criação de objetos sem a necessidade de instanciar a fábrica.
- **Produto:** A interface ou classe abstrata que define o tipo dos objetos que a fábrica criará.
- **Produtos Concretos:** As implementações específicas da interface ou classe abstrata `Produto`.

#### Composição

- **Client:** Classe principal que usa a fábrica.
- **Static Simple Factory:** Classe estática que provê métodos para selecionar qual instância do produto queremos criar.
- **Product:** Template de produto.
- **Product Instance:** Tipos específicos de produtos criados pela fábrica.

#### Considerações

- A `Simple Factory` é útil quando o foco é criar um único tipo de produto, sem a necessidade de interagir com outros produtos, como ocorre no `Abstract Factory`.

### Prototype

O padrão `Prototype` permite criar novos objetos ao copiar um "protótipo" existente em vez de criar novos do zero. Isso é útil quando o custo de criação de um objeto é alto (em termos de tempo ou recursos), e queremos evitar esse custo sempre que for necessário criar novos objetos do mesmo tipo.

#### Características

- **Clonagem:** O `Prototype` envolve a criação de uma interface ou classe base que define um método para clonar objetos. Os objetos que implementam essa interface podem, assim, ser copiados, permitindo a criação de novos objetos a partir de instâncias existentes.
- **Cópia Profunda ou Rasa:** Atenção especial deve ser dada ao tipo de cópia realizada (deep copy ou shallow copy).

#### Composição

- **Prototype (Protótipo):** Interface que declara o método `clone()`, responsável por retornar uma cópia do objeto.
- **ConcretePrototype (Protótipo Concreto):** Implementa a interface `Prototype` e define o método `clone()`.
- **Client (Cliente):** Usa o método `clone()` do `Prototype` para criar novos objetos.

#### Considerações

- O padrão `Prototype` é especialmente útil quando se trabalha com objetos cujas configurações iniciais são complexas ou caras de se obter.

### Singleton

O padrão `Singleton` garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global para essa instância.

#### Características

- **Instância Única:** Garante que apenas uma instância de uma classe seja criada e fornece um ponto de acesso global a essa instância.
- **Controle de Instância:** Muitas implementações do Singleton incluem controle para evitar a criação de múltiplas instâncias, mesmo em cenários de multi-threading.

#### Composição

- **Singleton:** A classe que será instanciada uma única vez.

#### Considerações

- **Atenção:** Use o `Singleton` com cuidado, pois pode introduzir problemas de testes e dificultar a manutenção do código, além de potencialmente violar o princípio da responsabilidade única.

### Object Pool

O padrão `Object Pool` é utilizado para reutilizar objetos que são caros para criar ou manter.

#### Características

- **Reuso de Objetos:** O `Object Pool` permite o reuso de objetos, evitando o custo de criação e destruição frequente.
- **Gestão de Recursos:** Útil para gerenciamento eficiente de recursos, como conexões de banco de dados, que são caras de criar e destruir.

#### Composição

- **Object Pool:** Mantém uma coleção de objetos reutilizáveis.
- **Client:** Classe que utiliza objetos da pool.

#### Considerações

- **Atenção:** Evite usar este padrão para economizar alguns megabytes de memória, a menos que seja um caso extremo. O objeto deve ser corretamente devolvido à pool; caso contrário, a economia não será eficaz. Além disso, as pools devem ter um tamanho aceitável - se forem muito grandes, podem perder o sentido.

## Padrões Estruturais (Structural Design Patterns)

Os padrões estruturais tratam da composição de classes ou objetos.

### Bridge

O `Bridge` é útil para separar a abstração de sua implementação, permitindo que ambos variem independentemente.

#### Características

- **Separação de Abstração e Implementação:** O `Bridge` separa a abstração (interface) da implementação, permitindo que elas evoluam independentemente.
- **Desacoplamento:** Reduz o acoplamento entre a abstração e a implementação.

#### Composição

- **Abstração:** Define a interface de alto nível e mantém uma referência à implementação.
- **Implementação:** Interface ou classe abstrata para a implementação real.
- **Concrete Implementations:** Implementações específicas da interface `Implementação`.

#### Considerações

- **Validade:** O `Bridge` é válido quando há a necessidade de manter uma relação fracamente acoplada, especialmente em casos onde a relação é do tipo 1 -> N.

### Composite

O `Composite` permite tratar objetos individuais e composições de objetos de forma uniforme.

#### Características

- **Hierarquia de Objetos:** Permite compor objetos em estruturas de árvore para representar hierarquias parte-todo.
- **Tratamento Uniforme:** Objetos individuais e composições de objetos são tratados da mesma maneira.

#### Composição

- **Component:** Interface comum para todos os objetos na composição.
- **Leaf:** Representa objetos individuais na composição.
- **Composite:** Representa a composição de objetos.

#### Considerações

- **Complexidade:** A implementação pode ser complexa e difícil de gerenciar, especialmente em hierarquias profundas.

### Facade

O `Facade` fornece uma interface simplificada para um conjunto de interfaces em um subsistema.

#### Características

- **Interface Simplificada:** O `Facade` oferece uma interface única e simplificada para um conjunto de interfaces mais complexas.
- **Isolamento de Complexidade:** Esconde a complexidade do subsistema do cliente.

#### Composição

- **Facade:** Interface simplificada que expõe funcionalidades principais de um subsistema.
- **Subsistemas:** Conjunto de classes complexas que o `Facade` abstrai.

#### Considerações

- **Uso Cauteloso:** Se você sentir a necessidade de usar um `Facade`, pode ser um sinal de que o sistema subjacente está mal estruturado.

### Flyweight

O `Flyweight` é um padrão que compartilha aspectos comuns entre objetos para economizar memória.

#### Características

- **Compartilhamento de Estados:** Compartilha estados intrínsecos entre objetos para reduzir o consumo de memória.
- **Redução de Instâncias:** Minimiza a quantidade de instâncias criadas.

#### Composição

- **Flyweight:** Interface comum para objetos que podem ser compartilhados.
- **ConcreteFlyweight:** Implementa a interface `Flyweight`, compartilhando estados.
- **UnsharedFlyweight:** Objetos que não podem ser compartilhados.
- **FlyweightFactory:** Cria e gerencia objetos `Flyweight`.

#### Considerações

- **Eficácia

:** O `Flyweight` é eficaz quando aplicado a um grande número de objetos, onde o compartilhamento de estados reduz significativamente o uso de memória.

### Adapter

O `Adapter` permite que classes com interfaces incompatíveis trabalhem juntas.

#### Características

- **Compatibilidade:** Permite a integração de classes que, de outra forma, não poderiam trabalhar juntas devido a interfaces incompatíveis.
- **Reutilização:** Facilita a reutilização de classes existentes sem modificação.

#### Composição

- **Target:** Interface esperada pelo cliente.
- **Adapter:** Converte a interface da classe existente para a interface esperada.
- **Adaptee:** Classe que precisa ser adaptada.

#### Considerações

- **Flexibilidade:** O `Adapter` oferece uma solução flexível, mas pode adicionar complexidade ao sistema.

## Padrões Comportamentais (Behavioral Design Patterns)

Os padrões comportamentais tratam da interação e responsabilidade entre os objetos.

### Observer

O `Observer` define uma dependência de um-para-muitos entre objetos, de forma que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.

#### Características

- **Relacionamento Um-para-Muitos:** Notifica automaticamente todos os objetos dependentes quando o estado do objeto observado muda.
- **Desacoplamento:** Facilita a comunicação entre objetos desacoplados.

#### Composição

- **Subject:** Mantém a lista de dependentes e os notifica sobre as mudanças de estado.
- **Observers:** Classes que implementam a interface para serem notificadas sobre mudanças.

#### Considerações

- **Gerenciamento de Dependências:** Pode ser difícil gerenciar uma grande quantidade de observadores, e o uso excessivo pode levar a um sistema difícil de depurar.

### Command

O `Command` encapsula uma solicitação como um objeto, permitindo parametrizar clientes com diferentes solicitações, enfileirar ou fazer o log de solicitações, e suportar operações que podem ser desfeitas.

#### Características

- **Encapsulamento de Solicitações:** Encapsula uma solicitação como um objeto, facilitando a parametrização e gerenciamento de solicitações.
- **Desfazer e Refazer:** Facilita a implementação de funcionalidades de desfazer e refazer.

#### Composição

- **Command:** Interface que declara o método de execução.
- **ConcreteCommand:** Implementa o comando específico.
- **Invoker:** Classe que invoca os comandos.
- **Receiver:** Classe que recebe o comando e realiza a ação correspondente.

#### Considerações

- **Flexibilidade:** Oferece flexibilidade na criação e execução de comandos, mas pode adicionar complexidade ao sistema.

### Chain of Responsibility

O `Chain of Responsibility` permite que múltiplos objetos tenham a chance de processar uma solicitação ao passá-la ao longo de uma cadeia de manipuladores.

#### Características

- **Processamento em Cadeia:** Permite que múltiplos manipuladores processem uma solicitação, com cada manipulador podendo escolher passar a solicitação adiante ou não.
- **Desacoplamento:** Desacopla o remetente da solicitação dos seus receptores.

#### Composição

- **Handler:** Interface para manipulação de solicitações.
- **ConcreteHandler:** Implementa o método para manipular a solicitação ou passá-la para o próximo manipulador.
- **Client:** Inicia a solicitação.

#### Considerações

- **Controle de Fluxo:** Pode ser difícil prever a ordem de execução dos manipuladores, especialmente em cadeias complexas.

### State

O `State` permite que um objeto altere seu comportamento quando o seu estado interno muda, como se a classe do objeto tivesse mudado.

#### Características

- **Mudança de Comportamento:** Permite que um objeto altere seu comportamento dinamicamente quando seu estado muda.
- **Estados Separados:** Cada estado é encapsulado em uma classe separada.

#### Composição

- **Context:** Mantém uma referência ao estado atual e permite a transição entre estados.
- **State:** Interface para todos os estados.
- **ConcreteState:** Implementações específicas de estados.

#### Considerações

- **Complexidade:** O padrão `State` pode aumentar a complexidade do código ao introduzir várias classes para representar os estados.

---
