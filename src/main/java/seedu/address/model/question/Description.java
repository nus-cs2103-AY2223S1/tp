package seedu.address.model.question;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Question's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Question should not be blank";

    public final String descriptionString;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        descriptionString = description;
    }


    @Override
    public String toString() {
        return descriptionString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                        && descriptionString.equals(((Description) other).descriptionString)); // state check
    }

    @Override
    public int hashCode() {
        return descriptionString.hashCode();
    }

}
