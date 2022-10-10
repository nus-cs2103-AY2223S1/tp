package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's id in the address book.
 */
public class Id {

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
