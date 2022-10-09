package seedu.address.model.note.exceptions;

/**
 * Signals that the operation will result in duplicate Note (Notes are considered duplicates if they have the same
 * title).
 */
public class DuplicateNoteException extends RuntimeException {
    public DuplicateNoteException() {
        super("Operation would result in duplicate note");
    }
}
