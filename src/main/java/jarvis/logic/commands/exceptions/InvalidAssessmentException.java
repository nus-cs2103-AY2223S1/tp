package jarvis.logic.commands.exceptions;

/**
 * Represents an error which occurs when an invalid Assessment value is supplied to a Command
 */
public class InvalidAssessmentException extends RuntimeException {
    public InvalidAssessmentException() {
        super("Command does not expect the given assessment value.");
    }
}
