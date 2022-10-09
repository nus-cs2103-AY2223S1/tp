package seedu.address.model.note.exceptions;

public class DuplicateNoteException extends RuntimeException{
    public DuplicateNoteException() {
        super("Operation would result in duplicate note");
    }
}
