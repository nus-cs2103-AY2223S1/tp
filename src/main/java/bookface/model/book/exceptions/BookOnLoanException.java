package bookface.model.book.exceptions;

/**
 * Signals that the operation cannot be performed since the {@code Book} is currently loaned out
 */
public class BookOnLoanException extends RuntimeException {
    public BookOnLoanException() {
        super("Book cannot be deleted; it is currently loaned out to someone!");
    }
}
