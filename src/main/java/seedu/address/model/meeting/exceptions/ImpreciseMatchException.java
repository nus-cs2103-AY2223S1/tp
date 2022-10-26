package seedu.address.model.meeting.exceptions;

/**
 * An exception that gets thrown during the creation of a new meeting when the predicate
 * for the name of a person to meet matches multiple contacts in the address book.
 */
public class ImpreciseMatchException extends RuntimeException {
    public ImpreciseMatchException() {
        super("Name predicate in new meeting matches multiple contacts");
    }
}
