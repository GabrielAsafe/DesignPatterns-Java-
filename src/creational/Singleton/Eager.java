package creational.Singleton;

public class Eager {

    private Eager(){

    }

    private static final Eager INSTANCE = new Eager();
    public static Eager getInstance(){
        return INSTANCE;
    }
}




class Client{

    public static void main(String[] args) {

        Eager eagerInstance = Eager.getInstance();

        Eager eagerInstance2 = Eager.getInstance();


        System.out.println(eagerInstance2 ==    eagerInstance);
    }

}
