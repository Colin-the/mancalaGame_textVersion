//This entire class is AI writen

package mancala;

public class Pit {
    private int stoneCount;

    public int getStoneCount() {
        return stoneCount;
    }

    public void addStone() {
        stoneCount++;
    }

    public int removeStones() {
        int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }
}
