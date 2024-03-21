package mancala;

public class PitNotFoundException extends RuntimeException {

    public PitNotFoundException() {
        super("Pit not found.");
    }

    public PitNotFoundException(String message) {
        super(message);
    }
}