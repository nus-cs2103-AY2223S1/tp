package seedu.address.model.tag;

import seedu.address.model.item.AbstractDisplayItem;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class UniqueID {
    public static final String MESSAGE_CONSTRAINTS = "UUID must be unique.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public String id;

    public UniqueID(String id) {
        this.id = id;
        requireNonNull(id);
        checkArgument(isValidUniqueID(id), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidUniqueID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueID // instanceof handles nulls
                && id.equals(((UniqueID) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + id + ']';
    }

    public void generateUniqueID(AbstractDisplayItem item) {
        this.id = String.valueOf(randomUUID());
    }
}
