package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a RiskTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class RiskTag extends Tag{

    public static final String MESSAGE_CONSTRAINTS = "Risk tag name should be [LOW], [MEDIUM], or [HIGH]";
    public static final String VALIDATION_REGEX = "(HIGH|MEDIUM|LOW)";

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public RiskTag(String tagName) {
        super(tagName);
        checkArgument(isValidRiskTagName(tagName));
    }


    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidRiskTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
