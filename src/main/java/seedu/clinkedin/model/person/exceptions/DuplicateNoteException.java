package seedu.clinkedin.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate notes for a person.
 */
public class DuplicateNoteException extends RuntimeException {
    public DuplicateNoteException(String note) {
        super("The following note already exixts: " + note);
    }
}
