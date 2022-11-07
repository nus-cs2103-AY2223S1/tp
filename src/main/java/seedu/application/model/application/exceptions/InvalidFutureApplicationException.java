package seedu.application.model.application.exceptions;

/**
 * Signals that the operation will result in adding/editing future Applications (Applications in CinternS should not be
 * later than the current date).
 */
public class InvalidFutureApplicationException extends RuntimeException {

    /**
     * Constructs an InvalidFutureApplicationException object.
     */
    public InvalidFutureApplicationException() {
        super("Operation would results future application exist in CinternS! "
                + "Please ensure the application date is not later than today.");
    }
}
