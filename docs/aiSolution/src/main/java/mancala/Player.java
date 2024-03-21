package mancala;

public class Player {
    private String name;
    private Store store;

    public Player() {// Default constructor
        name = "Player";
        store = new Store();
    }

    public Player(String name) {
        this.name = name;
    }
    
    public Store getStore() {
        return store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getStoreCount() {
        if (store != null) {
            return store.getTotalStones();
        } else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return name; 
    }
}
