package seedu.address.model.meeting.exceptions;

/**
 * An exception that gets thrown during the creation of a new meeting when the predicate
 * for the name of a person to meet matches multiple contacts in the address book.
 */
public class ImpreciseMatchException extends RuntimeException {

    /**
     * Constructor for ImpreciseMatchException
     *
     * @param impreciseName the name that causes ambiguity when the programme tries to find a matching contact
     */
    public ImpreciseMatchException(String impreciseName) {
        super(String.format("Name of person to meet: %1$s matches multiple contacts", impreciseName));
    }

}
