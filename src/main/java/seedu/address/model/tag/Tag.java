package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

import seedu.address.model.entry.EntryType;

/**
 * Represents a Tag in the penny wise application.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(EntryType, String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String INCOME_CONSTRAINTS = "Income tags must only be one of the following: \n"
            + "Salary, Allowance, Profit, Investment, Gifts, Others";
    public static final String EXPENDITURE_CONSTRAINTS = "Expenditure tags must only be one of the following: \n"
            + "Food, Groceries, Entertainment, Transport, Education, Housing, Others";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public static final List INCOME_TAGS = Arrays.asList(
            "Salary",
            "Allowance",
            "Profit",
            "Investment",
            "Gifts",
            "Others");
    public static final List EXPENDITURE_TAGS = Arrays.asList(
            "Food",
            "Groceries",
            "Entertainment",
            "Transport",
            "Education",
            "Housing",
            "Others");
    public final String tagName;

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
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        switch (type.getEntryType()) {
        case INCOME:
            if (!INCOME_TAGS.contains(test)) {
                return false;
            }
            break;
        case EXPENDITURE:
            if (!EXPENDITURE_TAGS.contains(test)) {
                return false;
            }
            break;
        default:
            return true;
        }
        return true;

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
        return tagName;
    }

}
