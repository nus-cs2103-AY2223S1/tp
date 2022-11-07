package jarvis.model.exceptions;

/**
 * Signals that the operation will add an invalid note.
 */
public class InvalidNoteException extends RuntimeException {
    public InvalidNoteException(String message) {
        super(message);
    }
}
