package mancala;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    private Player playerOne;
    private Player playerTwo;

    //ai writen method
    public void setUpPits() {
        pits = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Pit pit = new Pit(); // Creates a new Pit instance
            pits.add(pit); // Adds the Pit instance to the ArrayList
        }
    }

    public ArrayList<Pit> getPits() {//ai writen method
        return pits;
    }

    public ArrayList<Store> getStores() {//ai writen method
        return stores;
    }

    public void setUpStores() {//slightly changed from AI version
        stores = new ArrayList<>();
        Store store1 = new Store();
        store1.setOwner(playerOne);          
        stores.add(store1);

        Store store2 = new Store();
        store2.setOwner(playerTwo);
        stores.add(store2);
    }

    public void initializeBoard() {//ai writen method
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
        for (Pit pit : pits) {
            pit.removeStones();//take all the stones out of the pit
            for (int i = 0; i < 4; i++) {
                pit.addStone();//put 4 stones back into the pit
            }
        }
        for (Store store : stores){
            store.emptyStore();//take all of the stones out of both stores
        }
    }

    public void registerPlayers(Player one, Player two) {
        this.playerOne = one;
        this.playerTwo = two;

        // Set up stores
        setUpStores();

        // Connect each player to their respective store
        stores.get(0).setOwner(playerOne);
        stores.get(1).setOwner(playerTwo);
        playerOne.setStore(stores.get(0));
        playerTwo.setStore(stores.get(1));

    }

    public int moveStones(int startPit, Player player) {   
        if (startPit > 12 || startPit < 1){//if the pit number does not exsist (not betewen 1 and 12)
            throw new InvalidMoveException("Invalid move. This pit does not exist!");
        } else if ((player == playerOne && startPit > 6) || (player == playerTwo && startPit < 7)) {
            //if the player is trying to move stones they are not allowed to (player 1 tring to move player 2's stones)
            //throw expection
            throw new InvalidMoveException("Invalid move. You are not allowed to move stones in the given pit.");
        }
        
        int numStonesAddedToStore=0;
        int stonesInPit = getNumStones(startPit);
        if (player == playerOne){
            while(startPit+stonesInPit>=7){
                numStonesAddedToStore++;//add a stone to the players store
                stonesInPit=stonesInPit-13;//take away the number of stones that a full lap around the board would take
            }

            stores.get(0).addStones(numStonesAddedToStore);
        }else if (player == playerTwo){//for player 2
            while(startPit+stonesInPit>=13){
                numStonesAddedToStore++;//add a stone to the players store
                stonesInPit=stonesInPit-13;//take away the number of stones that a full lap around the board would take
            }

            stores.get(1).addStones(numStonesAddedToStore);
        }
        

        
        distributeStones(startPit);//distrbute the stones accros the board
        return numStonesAddedToStore; 
    }
    public int distributeStones(int startingPoint) {
        if (startingPoint < 1 || startingPoint > 12) {
            throw new PitNotFoundException("Invalid pit number. Pit number must be in the range of 1-12.");
        }
        int currentPit = startingPoint;   
        int stonesToDistribute = getNumStones(currentPit);   
        pits.get(currentPit-1).removeStones();//take all the stones out of the current pit
        boolean endOnStore = false;
        //we need to leave this outside the loop as it will take the stone for the 2nd players store out at the start
        if (currentPit==12){
            stonesToDistribute--;//this is the stone that would be going into the players store
            endOnStore=true;
        }
        while (stonesToDistribute > 0) {
            //this will move the current pit in the sequence 1,2,3...,11,12,1,2...
            currentPit = (currentPit + 1) % 12; 
            if (currentPit==0){
                currentPit=12;
            }  
            //if its player 1 moveing and we are at player 1's store
            if (currentPit == 7 && startingPoint > 1 && startingPoint <= 6) {
                stonesToDistribute--;//this is the stone that would be going into the players store 
                endOnStore=true;//presume this is the last stone
            }
            //make sure we have a stone to put in the next pit and it just didnt get taken by the store
            if (stonesToDistribute > 0){
                endOnStore=false;//as the last stone is going into a pit if we reach this case
                pits.get(currentPit-1).addStone();//add a stone to the pit and then take it away from the counter
                stonesToDistribute--;
            }
            //if its player 2 moveing and we are at player 2's store
            if (currentPit == 12 && startingPoint > 6 && startingPoint <= 12) {
                stonesToDistribute--;//this is the stone that would be going into the players store
                endOnStore=true; 
            }          
        }
        //only want to call captureStones IF we have moved peices around and end back on our side of the board    
        if (getNumStones(currentPit)==0){//if we are ending on a empty pit
             //if we start and end on player1's side of the board and not a store
            if ((startingPoint > 1 && startingPoint < 7) && (currentPit > 1 && currentPit < 7) && !endOnStore){
                captureStones(currentPit);
            }
            //if we start and end on player2's side of the board
            if ((startingPoint > 6 && startingPoint < 13)&&(currentPit > 6 && currentPit < 13)&& !endOnStore){
                captureStones(currentPit);
            }
        }
        return stonesToDistribute;
    }

    public int captureStones(int stoppingPoint) {
        int pitTakeFrom=0;
        if (stoppingPoint < 1 || stoppingPoint > 12) {
            throw new PitNotFoundException("Invalid pit number. Pit number must be in the range of 1-12.");
        }else if (stoppingPoint < 7){//player 1 taking player 2's stones
            pitTakeFrom=13-stoppingPoint;//get oppside pit number
            stores.get(0).addStones(pitTakeFrom);//take the stones from the player 2's pit and put them in 1's store
            stores.get(0).addStones(stoppingPoint);//take the stone we just used to capture and put it in the store
            return pits.get(pitTakeFrom - 1).removeStones();
        }else {//player 2 taking player 1's stones
            pitTakeFrom=13-stoppingPoint;//get oppside pit number
            stores.get(1).addStones(pitTakeFrom);//take the stones from the player 2's pit and put them in 1's store
            stores.get(1).addStones(stoppingPoint);//take the stone we just used to capture and put it in the store
            return pits.get(pitTakeFrom - 1).removeStones();
        }

        //if the stopping point is on the players side (pits 1-6 for player 1, pits 7-12 for player 2)
        //AND the stopping point is empty
        //Then take ALL stones in the oppsite pit and place them in 
    }

    public int getNumStones(int pitNum) {//ai writen method
        return pits.get(pitNum - 1).getStoneCount(); // Pits are numbered 1-12 but 0-11 in the array list
    }

    public boolean isSideEmpty(int pitNum) {
        if (1>=pitNum && pitNum<=6){//first 6 pits
            for (int i = 1; i <= 6; i++) {
                if (getNumStones(i) != 0) {
                    return false; // at least one pit has stones
                }
            }
            return true; // all pits are empty
        }else if(7>=pitNum && pitNum<=12){//last 6 pits
            for (int i = 7; i <= 12; i++) {
                if (getNumStones(i) != 0) {
                    return false; // at least one pit has stones
                }
            }
            return true; // all pits are empty
        }else{
            throw new PitNotFoundException("Invalid pit number. Pit number must be in the range of 1-12.");
        }
        
    }
}
