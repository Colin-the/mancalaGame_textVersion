//This entire class is AI writen

package mancala;

public class Player {
    private String name;
    private Store store;

    public Player() {// Default constructor
        name = "Player";
        store = new Store();
    }

    public Player(String givenName) {
        this.name = givenName;
    }

    public Store getStore() {
        return store;
    }

    public String getName() {
        return name;
    }

    public void setName(String givenName) {
        this.name = givenName;
    }

    public void setStore(Store givenStore) {
        this.store = givenStore;
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
