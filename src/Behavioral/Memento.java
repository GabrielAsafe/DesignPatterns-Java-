package Behavioral;


import java.util.Stack;

/**
 * O padrão de design Memento é usado para capturar e armazenar o estado interno de um objeto, sem violar o encapsulamento,
 * para que o objeto possa ser restaurado para esse estado posteriormente. Esse padrão é útil em cenários como sistemas de desfazer/refazer,
 * onde é necessário restaurar o estado anterior de um objeto.
 */
class Memento {
    private final String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class TextEditor {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Memento save() {
        return new Memento(content);
    }

    public void restore(Memento memento) {
        this.content = memento.getState();
    }
}



//O Caretaker gerencia o histórico de estados (mementos) e realiza operações de desfazer/restaurar.
class History {
    private Stack<Memento> history = new Stack<>();

    public void save(TextEditor editor) {
        history.push(editor.save());
    }

    public void undo(TextEditor editor) {
        if (!history.isEmpty()) {
            editor.restore(history.pop());
        } else {
            System.out.println("Nada para desfazer!");
        }
    }
}


class MementoPatternExample {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        editor.setContent("Versão 1");
        history.save(editor);
        System.out.println("Conteúdo Atual: " + editor.getContent());

        editor.setContent("Versão 2");
        history.save(editor);
        System.out.println("Conteúdo Atual: " + editor.getContent());

        editor.setContent("Versão 3");
        System.out.println("Conteúdo Atual: " + editor.getContent());

        // Desfazer a última alteração
        history.undo(editor);
        System.out.println("Depois de desfazer: " + editor.getContent());

        // Desfazer novamente
        history.undo(editor);
        System.out.println("Depois de desfazer: " + editor.getContent());
    }
}
