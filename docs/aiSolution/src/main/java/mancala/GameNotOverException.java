package mancala;

public class GameNotOverException extends RuntimeException {

    public GameNotOverException() {
        super("The game is not over yet.");
    }

    public GameNotOverException(String message) {
        super(message);
    }
}
