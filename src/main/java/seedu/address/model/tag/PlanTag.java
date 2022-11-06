package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a PlanTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidPlanTagName(String)}
 */
public class PlanTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Plan tag name should only contain alphanumeric characters "
            + "and spaces, ending with Plan (case insensitive)";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public PlanTag(String tagName) {
        super(tagName);
        checkArgument(isValidPlanTagName(tagName), MESSAGE_CONSTRAINTS);
    }


    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidPlanTagName(String test) {
        return test.matches(VALIDATION_REGEX) && test.trim().toUpperCase().endsWith(" PLAN");
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlanTag // instanceof handles nulls
                && tagName.equals(((PlanTag) other).tagName)); // state check
    }

}
