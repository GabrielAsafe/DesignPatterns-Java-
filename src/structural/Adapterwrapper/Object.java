package structural.Adapterwrapper;

// Target Interface
 interface Target2 {
    void request();
}

// Old System that needs adaptation
 class OldSystem2 {
    public void specificRequest() {
        System.out.println("Called specificRequest from OldSystem.");
    }
}

// Object Adapter that adapts OldSystem to Target interface
 class ObjectAdapter implements Target2 {
    private OldSystem2 oldSystem;

    public ObjectAdapter(OldSystem2 oldSystem) {
        this.oldSystem = oldSystem;
    }

    @Override
    public void request() {
        // Adapta a chamada para o método específico do OldSystem
        oldSystem.specificRequest();
    }
}

// Client using the Target interface
 class Client2 {
    public static void main(String[] args) {
        OldSystem2 oldSystem = new OldSystem2();
        Target2 adapter = new ObjectAdapter(oldSystem);
        adapter.request();  // Output: Called specificRequest from OldSystem.
    }
}
