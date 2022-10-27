package bookface.model.person.exceptions;

/**
 * Signals that the operation cannot be performed since the {@code Person} still
 * has {@code Book}s on loan
 */
public class PersonLoansExistException extends RuntimeException {
    public PersonLoansExistException() {
        super("Person cannot be deleted; there are loans that are not settled!");
    }
}
