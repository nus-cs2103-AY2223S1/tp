package jarvis.model.exceptions;

/**
 * Signals that the operation will set an invalid participation value for a student.
 */
public class InvalidParticipationException extends RuntimeException {
    public InvalidParticipationException(String message) {
        super(message);
    }
}
