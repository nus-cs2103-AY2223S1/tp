package jarvis.logic.commands.exceptions;

public class InvalidMarkException extends RuntimeException {
    public InvalidMarkException() {
        super("Mark cannot be less than 0 or greater than the total mark of the assessment");
    }
}
