package structural.Decorator;


interface Message{
    String getMessage();
}

class Base64EncodedMessage implements Message{

    Message message;

    public Base64EncodedMessage(Message message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return  message.getMessage() +" encoded as base64";
    }
}

class HtmlEncodedMessage implements Message{


    private Message message;

    public HtmlEncodedMessage(Message message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getMessage() +" Encoded as html";
    }
}






class TextMessage implements Message{

    private String message;

    public TextMessage(String msg){
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

class Decoratror{
    public static void main(String[] args) {
        Message mensagem = new TextMessage("sua putinha suja");

        System.out.println(mensagem.getMessage());


        Message m2 = new Base64EncodedMessage(mensagem);

        System.out.println(m2.getMessage());
    }
}


