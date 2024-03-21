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
        currentPlayer = onePlayer;
        board = new Board();
    }
    
    public void setPlayers(Player onePlayer, Player twoPlayer) {
        this.onePlayer = onePlayer;
        this.twoPlayer = twoPlayer;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(onePlayer);
        players.add(twoPlayer);
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void setBoard(Board theBoard) {
        board = theBoard;
    }

    public Board getBoard() {
        return board;
    }

    public int getNumStones(int pitNum) {
        return board.getNumStones(pitNum);
    }

    public int move(int startPit) {
        // Implement the logic for moving stones here
        return 0; // Placeholder, replace with actual implementation
    }

    public int getStoreCount(Player player) {
        int playerStoreIndex = board.getStores().indexOf(player);

        // If the player's store is found, use the getTotalStones method
        if (playerStoreIndex != -1) {
            Store playerStore = board.getStores().get(playerStoreIndex);
            return playerStore.getTotalStones();
        } else {
            // Handle the case where the player's store is not found (return -1 or throw an exception, as needed)
            return -1; // Placeholder, replace with appropriate handling
        }
    }

    public Player getWinner() {
        // Implement logic to determine the winner
        return null; // Placeholder, replace with actual implementation
    }

    public boolean isGameOver() {
        // Implement logic to check if the game is over
        return false; // Placeholder, replace with actual implementation
    }

    public void startNewGame() {
        // Initialize the game with default values
        onePlayer = new Player("Player 1");
        twoPlayer = new Player("Player 2");
        currentPlayer = onePlayer;
        board = new Board();
    }

    @Override
    public String toString() {
        // Implement a string representation of the game state
        return "MancalaGame state"; // Placeholder, replace with actual implementation
    }
}
