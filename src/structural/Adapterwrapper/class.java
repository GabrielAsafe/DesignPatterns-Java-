package structural.Adapterwrapper;


/**
 * temos dois tipos de adaptador. adaptador de classe e adaptador de objeto
 */


// Target Interface
 interface Target {
    void request();
}

// Old System that needs adaptation
 class OldSystem {
    public void specificRequest() {
        System.out.println("Called specificRequest from OldSystem.");
    }
}

// Class Adapter that adapts OldSystem to Target interface
 class ClassAdapter extends OldSystem implements Target {
    @Override
    public void request() {
        // Adapta o método da classe herdada para a interface alvo
        specificRequest();
    }
}

// Client using the Target interface
 class Client {
    public static void main(String[] args) {
        Target adapter = new ClassAdapter();
        adapter.request();  // Output: Called specificRequest from OldSystem.
    }
}
