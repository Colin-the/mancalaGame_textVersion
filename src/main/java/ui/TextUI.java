package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
//import all of the files from the mancala package
import mancala.MancalaGame;
import mancala.Player;
import mancala.Board;
//import mancala.Pit;//not currently in use
//import mancala.Store;//not currently in use
import mancala.PitNotFoundException;
// import mancala.NoSuchPlayerException;//not currently in use
import mancala.InvalidMoveException;
import mancala.GameNotOverException;

public class TextUI {
    private MancalaGame game;
    private Scanner scanner;
    private ArrayList<Integer> intList;

    public TextUI() {//ai generated method
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        textUI.setUpGame();//get the game ready

        textUI.runGame();//this method will handle all opperation of the game

        textUI.printWinner();//will display the winner at the end
    }

    public void setUpGame() {//get the game ready
        game = new MancalaGame();
        Board board = new Board();
        board.initializeBoard();
        game.setBoard(board);
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        game.setPlayers(player1, player2);
        game.startNewGame();
    }

    private void printWinner() {//will display the winner 
        Player winner = null;
        try{
            winner=game.getWinner();
        }catch (GameNotOverException e) {// Handle the exception
            System.out.println(e.getMessage()+" Keep on playing!");
        }
        if (winner!=null){
            System.out.println("The Game Has Ended!!!");
            System.out.println("");
            System.out.println("");
            System.out.println("And the winner is "+winner+"!!!");
            System.out.println("Player 1 has "+gNSIS(1)+" stones in there store");
            System.out.println("Player 2 has "+gNSIS(2)+" stones in there store");
        }
        
    }

    public void displayBoard() {//prints the board after each turn
        drawHorizontalLine();
        drawPitsRow(2, 6);
        drawMiddleLine();

        // Draw the bottom row
        drawPitsRow(1, 6);
        drawHorizontalLine();
    }

    private void drawPitsRow(int playerNumber, int numPits) {//prints the pits with the correct value for each
        System.out.print("|       ");//to add in the padding for the 2nd players store
        if (playerNumber==1){//display the pits for the first player
            for (int pit = 1; pit <= 6; pit++) {
                System.out.print("|   " + getNumStonesInPit(pit) + "   ");
            }
        } else if (playerNumber==2){//display the pits for the first player
            for (int pit = 12; pit >= 7; pit--) {
                System.out.print("|   " + getNumStonesInPit(pit) + "   ");
            }
        }
        
        System.out.println("|       |");
    }

    private int getNumStonesInPit(int pitNum) {//to help display the pits
        return game.getBoard().getNumStones(pitNum);
    }

    private void drawHorizontalLine() {
        System.out.println("+-------+-------+-------+-------+-------+-------+-------+-------+");
    }
    private void drawMiddleLine() {
        System.out.println("+   "+gNSIS(2)+"   +-------+-------+-------+-------+-------+-------+   "+gNSIS(1)+"   +");
    }
    
    private int gNSIS(int storeNumber) {//to help display the stores
        return game.getPlayers().get(storeNumber-1).getStoreCount();
    }

    //will check if we have stones in the pit we are attempting to access
    private int getNumMovingStones(int moveingFrom){
        int numStones = -1;
        try{
            numStones=game.getNumStones(moveingFrom);
        }catch (PitNotFoundException e) {// Handle the exception          
            System.out.print(e.getMessage()+" Try entering a number beteween ");
            if (game.getCurrentPlayer() == game.getPlayers().get(0)){//player1
                System.out.print("1 and 6.");
            }else if (game.getCurrentPlayer() == game.getPlayers().get(1)){//player2
                System.out.print("7 and 12.");
            }
        }
        return numStones;//return the number of stones in the pit, -1 if empty
    }


    private List<Integer> getValidMove(int stonesBeingMoved,int stonesBeingAddedToStore,int moveingFrom){
        List<Integer> result = new ArrayList<>();
        while (stonesBeingAddedToStore==-1){//until we have a vaild move
                System.out.println("");
                System.out.print(game.getCurrentPlayer()+" enter the pit you want to move stones from: ");
                moveingFrom = scanner.nextInt();

                stonesBeingMoved=getNumMovingStones(moveingFrom);

                if (stonesBeingMoved==0){//if there are no stones in the selected pit the user needs to pick a diffrent
                    System.out.println("Looks like the Pit you've selected"
                     +"currently has no stones in it. Please enter a different pit number.");
                }else{//if we have stones to move we can proced with moving them
                    try {
                        stonesBeingAddedToStore=game.move(moveingFrom);
                    }catch (InvalidMoveException e) {// Handle the exception
                        System.out.print(e.getMessage()+" try entering a number beteween ");
                        if (game.getCurrentPlayer() == game.getPlayers().get(0)){//player1
                            System.out.print("1 and 6.");
                        }else if (game.getCurrentPlayer() == game.getPlayers().get(1)){//player2
                            System.out.print("7 and 12.");
                        }
                    }
                }
                
            }
            //since we need to return 2 things we can just add them to a array list and return that
            result.add(moveingFrom);
            result.add(stonesBeingMoved);
            return result;
    }

    public void runGame() {
        while (!game.isGameOver()){//while both players still have stones on there sides of the board
            displayBoard();//out put the board
            List<Integer> results = new ArrayList<>();
            
            int stonesBeingMoved=-1;
            int stonesBeingAddedToStore=-1;
            int moveingFrom=0;
            
            //get the usr input (it will be a vaild one when returned)
            results=getValidMove(stonesBeingMoved,stonesBeingAddedToStore,moveingFrom);
            //get the values out of the array list
            moveingFrom=results.get(0);
            stonesBeingMoved=results.get(1);

            //if the first player is moving and will end with a stone in there pit
            if ((game.getCurrentPlayer() == game.getPlayers().get(0))&&(stonesBeingMoved+moveingFrom)==7){
                //do nothing of note as the first player will get to go agin 
                System.out.println("");
                System.out.print(game.getCurrentPlayer()+" will get to go again as there turn ended on there store.");
                System.out.println("");
                //if the Second player is moving and will end with a stone in there pit
            }else if ((game.getCurrentPlayer() == game.getPlayers().get(1))&&(stonesBeingMoved+moveingFrom)==13){
                //do nothing of note as the second player will get to go agin 
                System.out.println("");
                System.out.print(game.getCurrentPlayer()+" will get to go again as there turn ended on there store.");
                System.out.println("");
            }else{//if the move does not end on a store
                //change whos turn it is
                if (game.getCurrentPlayer() == game.getPlayers().get(0)){
                    game.setCurrentPlayer(game.getPlayers().get(1));
                }else if (game.getCurrentPlayer() == game.getPlayers().get(1)){
                    game.setCurrentPlayer(game.getPlayers().get(0));
                }
            }

            //keep looping until some one wins the game!
        }

    }

    
}
