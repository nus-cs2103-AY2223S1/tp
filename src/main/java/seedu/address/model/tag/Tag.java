package seedu.address.model.tag;

import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.Income;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the penny wise application.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(EntryType, String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public final String tagName;
    public static final List incomeTags = Arrays.asList(
            "Salary",
            "Allowance",
            "Profit",
            "Investment",
            "Gifts",
            "Others" );
    public static final List expenditureTags = Arrays.asList(
            "Food",
            "Groceries",
            "Entertainment",
            "Transport",
            "Education",
            "Housing",
            "Others");
    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(EntryType type, String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(type, tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(EntryType type, String test) {
        switch (type.getEntryType()) {
        case INCOME:
            if (!incomeTags.contains(test)) {
                return false;
            }
        case EXPENDITURE:
            if (!expenditureTags.contains(test)) {
                return false;
            }
        default:
            return test.matches(VALIDATION_REGEX);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Tag // instanceof handles nulls
            && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
