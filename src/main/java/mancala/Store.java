//This entire class is AI writen

package mancala;

public class Store {
    private Player owner;
    private int stoneCount=0;

    public void setOwner(Player player) {
        owner = player;
        if (owner != null) {
            owner.setStore(this);  // Associate the store with the player
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void addStones(int amount) {
        stoneCount += amount;
    }

    public int getTotalStones() {
        return stoneCount;
    }

    public int emptyStore() {
        int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }
}
