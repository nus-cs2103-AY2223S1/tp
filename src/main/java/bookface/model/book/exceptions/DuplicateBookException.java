package bookface.model.book.exceptions;

/**
 * Signals that the operation will result in duplicate Books (Books are considered duplicates if they have the same
 * details).
 */
public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException() {
        super("Operation would result in duplicate books");
    }
}
