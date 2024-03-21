package ui;

import mancala.MancalaGame;
import mancala.Player;

import java.util.Scanner;

public class TextUI {
    private MancalaGame mancalaGame;
    private Scanner scanner;

    public TextUI() {
        // Initialize the scanner for user input
        scanner = new Scanner(System.in);
    }
    public void displayBoard() {
        drawHorizontalLine();
        drawPitsRow(1, 6);
        drawHorizontalLine();

        // Draw the bottom row
        drawHorizontalLine();
        drawPitsRow(6, 1);
        drawHorizontalLine();
    }

    private void drawPitsRow(int row, int numPits) {
        for (int pit = 1; pit <= numPits; pit++) {
            System.out.print("|   " + getPitSymbol(row, pit) + "   ");
        }
        System.out.println("|");
    }

    private char getPitSymbol(int row, int pit) {
        // You can customize this method to get the symbols based on your game state
        return 'O'; // Placeholder, replace with actual logic
    }

    private void drawHorizontalLine() {
        System.out.println("+-------+-------+-------+-------+-------+-------+");
    }

    private void drawVerticalLine() {
        System.out.println("|       |       |       |       |       |       |");
    }

    public void startGame() {
        mancalaGame = new MancalaGame();
        

        //Create and initialize the MancalaGame
        //mancalaGame.initializeGame();

        // Main game loop
        /*while (!mancalaGame.isGameOver()) {
            printGameState();
            playTurn();
        }*/

        // Print the final state of the board and declare the winner
        
    }

    private void printGameState() {
        // Implement logic to print the current state of the game (board, stones, etc.)
        // Example: Print the board, player scores, etc.
    }

    private void playTurn() {
        // Implement logic to handle a player's turn
        // Example: Prompt the current player for their move, handle the move, etc.
    }

    private void printWinner() {
        // Implement logic to print the winner of the game
        // Example: Print the winner's name and final scores
    }

    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        textUI.displayBoard();
        textUI.startGame();
        textUI.printGameState();
        textUI.printWinner();
    }
}
