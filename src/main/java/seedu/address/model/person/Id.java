package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's id in the address book.
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS = "Id should only contain 3 digits and 1 character";
    public static final String VALIDATION_REGEX = "\\d{3}[a-zA-Z]";
    public final String value;

    /**
     * Constructor for Id.
     * @param id A valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        value = id.toUpperCase();
    }
}
