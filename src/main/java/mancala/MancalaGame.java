package mancala;

import java.util.ArrayList;

public class MancalaGame { 
    private Player onePlayer;
    private Player twoPlayer;
    private Player currentPlayer;
    private Board board;

    // Constructor
    public MancalaGame() {
        // Initialize the game with default values
        onePlayer = new Player("Player 1");
        twoPlayer = new Player("Player 2");
        setCurrentPlayer(onePlayer);
    }

    public void setPlayers(Player playerOne, Player playerTwo) {
        this.onePlayer = playerOne;
        this.twoPlayer = playerTwo;
        setCurrentPlayer(onePlayer);
        if (board!=null){
            board.registerPlayers(onePlayer,twoPlayer);
        }
    }

    public ArrayList<Player> getPlayers() {//ai writen method
        ArrayList<Player> players = new ArrayList<>();
        players.add(onePlayer);
        players.add(twoPlayer);
        return players;
    }

    public Player getCurrentPlayer() {//ai writen method
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    public Board getBoard() {//ai writen method
        return board;
    }

    public int getNumStones(int pitNum) {
        if (pitNum < 1 || pitNum > 12) {//throw a expection if pitNum is not 1-12
            throw new PitNotFoundException("Invalid pit number");
        }
        return board.getNumStones(pitNum);
    }

    public int move(int startPit) {
        //this will move the peices around the board to the correct pits
        int numStonesAddedToStore = board.moveStones(startPit,currentPlayer);       
        return numStonesAddedToStore; 
    }
    
    public int getStoreCount(Player player) {
        if (player == onePlayer){
            //get the list of stores for the first player and then use the getTotalStones to return stoneCount
            return board.getStores().get(0).getTotalStones();
        }else if (player == twoPlayer){
            //get the list of stores for the second player and then use the getTotalStones to return stoneCount
            return board.getStores().get(1).getTotalStones();
        }else{
            //if no store is found throw expection
            throw new NoSuchPlayerException("Players store not found");
        }
    }

    public Player getWinner() throws GameNotOverException {
        if (!isGameOver()) {//if the game has not ended
            throw new GameNotOverException("Game is not over yet");
        }
    
        //get the total num of stones in both stores
        int numExtraStonePlayerOne=0;
        for (int i = 0; i <6; i++){
            numExtraStonePlayerOne+=board.getPits().get(i).getStoneCount();
        }
        board.getStores().get(0).addStones(numExtraStonePlayerOne);

        int numExtraStonePlayerTwo=0;
        for (int i = 6; i <12; i++){
            numExtraStonePlayerTwo+=board.getPits().get(i).getStoneCount();
        }
        board.getStores().get(1).addStones(numExtraStonePlayerTwo);


        int player1StonesInStore = getStoreCount(onePlayer);
        int player2StonesInStore = getStoreCount(twoPlayer);
    
        //if the first player ended up with more stones in there store then they win
        if (player1StonesInStore > player2StonesInStore) {
            return onePlayer;
        } else if (player1StonesInStore < player2StonesInStore) {//if the 2nd player ended with more stones they win
            return twoPlayer;
        } else {//if they ended up with the same number of stones then its a tie
            return null; //null means we have a tie
        }
    }

    public boolean isGameOver() {
        return board.isSideEmpty(1) || board.isSideEmpty(7);
    }

    public void startNewGame() {
        if (onePlayer == null || twoPlayer == null) {// Players are not set, initialize with default values
            onePlayer = new Player("Player 1");
            twoPlayer = new Player("Player 2");
        }
        setCurrentPlayer(onePlayer); // Set the default starting player

        if (board == null){//if there is no current board when this function runs
            board = new Board();//make a new board
            board.initializeBoard();
        }else{
            board.resetBoard();
        }
        
    }    
    //ai writen method
    @Override
    public String toString() {
        // Implement a string representation of the game state
        return "MancalaGame state"; // Placeholder, replace with actual implementation
    }
}
