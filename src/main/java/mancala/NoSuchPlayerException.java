package mancala;

public class NoSuchPlayerException extends RuntimeException {

    public NoSuchPlayerException() {
        super("Player not found.");
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }
}
