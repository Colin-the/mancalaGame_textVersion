package mancala;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    private Player playerOne;
    private Player playerTwo;

    public void setUpPits() {
        pits = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Pit pit = new Pit(); // Creates a new Pit instance
            pits.add(pit); // Adds the Pit instance to the ArrayList
        }
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setUpStores() {
        stores = new ArrayList<>();
        Store s1 = new Store();
        Store s2 = new Store();
        s1.setOwner(playerOne);
        s2.setOwner(playerTwo);
        stores.add(s1);
        stores.add(s2);
    }

    public void initializeBoard() {
        setUpPits();
        setUpStores();
        // Fill each pit with 4 stones
        for (Pit pit : pits) {
            for (int i = 0; i < 4; i++) {
                pit.addStone();
            }
        }
    }

    public void resetBoard() {
        setUpPits();
        distributeStones(4); // Redistribute stones
    }

    public void registerPlayers(Player one, Player two) {
        playerOne = one;
        playerTwo = two;
    }

    public int moveStones(int startPit, Player player) {
        // Implement logic to move stones
        return 0; // Placeholder, replace with actual implementation
    }

    public int distributeStones(int startingPoint) {
        int currentPit = startingPoint;
    
        int stonesToDistribute = pits.get(currentPit).getStoneCount();
    
        pits.get(currentPit).removeStones();//take all the stones out of the current pit
    
        while (stonesToDistribute > 0) {
            currentPit = (currentPit + 1) % 12; // Move to the next pit in a circular fashion
    
            // Skip opponent's store (assuming opponent's store is pit 6)
            if (currentPit == 6 && !isCurrentPlayerStore(currentPit)) {
                currentPit = (currentPit + 1) % 12;
            }
    
            pits.get(currentPit).addStone();
            stonesToDistribute--;
        }
    
        return currentPit;
    }
    
    private boolean isCurrentPlayerStore(int pitNumber) {
        // Assuming player's store is pit 12 for player one and pit 6 for player two
        if (playerOne != null && pitNumber == 12) {
            return true;
        } else if (playerTwo != null && pitNumber == 6) {
            return true;
        }
        return false;
    }

    public int captureStones(int stoppingPoint) {
        // Implement logic to capture stones
        return 0; // Placeholder, replace with actual implementation
    }

    public int getNumStones(int pitNum) {
        return pits.get(pitNum - 1).getStoneCount(); // Pits are numbered 1-12
    }

    public boolean isSideEmpty(int pitNum) {
        // Implement logic to check if a side is empty
        return false; // Placeholder, replace with actual implementation
    }
}
