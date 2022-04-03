package dev.shph.commandeur.container;

public class CommandeurException extends RuntimeException {
    public CommandeurException() {
        super();
    }

    public CommandeurException(String message) {
        super(message);
    }

    public CommandeurException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandeurException(Throwable cause) {
        super(cause);
    }
}
