package seedu.address.model.person.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PersonNotFoundException extends RuntimeException {

    public static final String PERSON_NOT_FOUND = "The person with the name: %1$s is not found";
    public static final String NO_PERSON_DETECTED = "Make sure if you have entered at least one name.";
    public PersonNotFoundException(String cause) {
        super(cause);
    }

}
