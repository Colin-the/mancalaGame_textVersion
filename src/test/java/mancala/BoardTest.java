import mancala.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeBoard();
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        board.registerPlayers(player1, player2);
    }
    private int getPitValue(int pit){
        return board.getPits().get(pit-1).getStoneCount();
    }

    private int getStoreValue(int store){
        return board.getStores().get(store-1).getTotalStones();
    }

    @Test
    public void testSetUpPitsAndGetPits() {

        for (Pit pit : board.getPits()) {
            assertEquals(4, pit.getStoneCount());
        }
    }



    @Test
    public void testMoveStonesValidMove() throws InvalidMoveException,PitNotFoundException {
        int startPit = 3;
        int stonesAddedToStore = board.moveStones(startPit, player1);
        assertEquals(5,getPitValue(4));
        assertEquals(1,player1.getStoreCount());
    }

    @Test
    public void testPlayer2MoveStonesValidMove() throws InvalidMoveException,PitNotFoundException {
        int startPit = 9;
        int stonesAddedToStore = board.moveStones(startPit, player2);
        assertEquals(5,getPitValue(10));
        assertEquals(1,player2.getStoreCount());
    }

    @Test
    public void testTwoVaildMoves() throws InvalidMoveException,PitNotFoundException {
        int startPit = 6;
        int stonesAddedToStore = board.moveStones(startPit, player1);
        startPit = 9;
        stonesAddedToStore = board.moveStones(startPit, player2);
        assertEquals(5,getPitValue(8));//as one stone from pit 6 should be here
        assertEquals(0,getPitValue(6));//should be empty as we took all the stones from here
        assertEquals(0,getPitValue(9));//should be empty as we took all the stones from here
        assertEquals(5,getPitValue(1));
        assertEquals(1,player1.getStoreCount());
        assertEquals(1,player2.getStoreCount());
    }


    @Test
    public void testMoveStonesInvalidMove() {
        assertThrows(InvalidMoveException.class, () -> board.moveStones(14, player1));
    }

    @Test
    public void testMoveStonesCaptureStones() throws InvalidMoveException {
        board.moveStones(1, player1); // Move stones to set up capture
        board.moveStones(7, player2); // Player 2 moves to capture stones
        assertEquals(0, board.getNumStones(1)); // Stones should be captured
    }

    @Test
    public void testCaptureStonesInvalidPit() {
        assertThrows(PitNotFoundException.class, () -> board.captureStones(0));
    }

    @Test
    public void testCaptureStonesPlayer1Capture() {
        board.moveStones(1, player1); //Move stones to set up capture
        board.captureStones(1); //Capture stones from the setup
        assertEquals(0, board.getNumStones(1)); 
    }

    @Test
    public void testCaptureStonesPlayer2Capture() {
        board.moveStones(7, player2); //move stones to set up capture
        board.captureStones(7); //Capture stones from the setup
        assertEquals(0, board.getNumStones(7)); //tones should be captured
        assertEquals(0, board.getNumStones(13 - 7)); //Opponent's pit should be empty
    }
}
