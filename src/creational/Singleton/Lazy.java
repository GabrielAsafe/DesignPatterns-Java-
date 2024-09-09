package creational.Singleton;

public class Lazy {

    private Lazy() {

    }

    private static volatile Lazy Instance; //força o valor ser lido/escrito da memória principal ao invés de ser lido da cache

    public static Lazy getInstance() {

        if (Instance == null) {
            synchronized (Lazy.class) {// Java offers a mechanism to avoid race conditions by synchronizing thread access to shared data. || ou seja isso é o famigerado LOCK
                if (Instance == null) {
                    Instance = new Lazy();
                }
            }

        }

        return Instance;
    }


}
class Client2{

    public static void main(String[] args) {

        Lazy eagerInstance = Lazy.getInstance();
        Lazy eagerInstance2 = Lazy.getInstance();

        System.out.println(eagerInstance == eagerInstance2);
    }

}