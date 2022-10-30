// @@author mohamedsaf1
package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.item.AbstractDisplayItem;

/**
 * A class for an unique ID for each item.
 */
public class UniqueID {
    public static final String MESSAGE_CONSTRAINTS = "UUID must be unique.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private String id;

    /**
     * A constructor to create an UUID object.
     *
     * @param id is the ID generated when instantiating an item object.
     */
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
        this.id = String.valueOf(item);
    }
}
