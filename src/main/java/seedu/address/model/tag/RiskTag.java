package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a RiskTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class RiskTag extends SpecialTag {

    public static final String MESSAGE_CONSTRAINTS = "Risk tag name should be [LOW], [MEDIUM], or [HIGH]";
    public static final String VALIDATION_REGEX = "(HIGH|MEDIUM|LOW)";
    public static final String HIGH = "HIGH";
    public static final String LOW = "LOW";
    public static final String MEDIUM = "MEDIUM";

    private int risk;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public RiskTag(String tagName) {
        super(tagName);
        checkArgument(isValidRiskTagName(tagName), MESSAGE_CONSTRAINTS);
        // only setRisk after we are sure that tagName is valid
        this.risk = setRisk(tagName);
    }

    public int getRisk() {
        return this.risk;
    }

    public int setRisk(String tagName) {
        switch (tagName) {

        case HIGH:
            return this.risk = 3;

        case MEDIUM:
            return this.risk = 2;

        case LOW:
            return this.risk = 1;

        default:
            return this.risk = 0;
        }
    }

    /**
     * check if tag is a risk tag
     * @param tag
     * @return true if its a risk tag, false if otherwise
     */
    public static boolean isRiskTag(String tag) {
        return HIGH.equalsIgnoreCase(tag)
                || MEDIUM.equalsIgnoreCase(tag)
                || LOW.equalsIgnoreCase(tag);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidRiskTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RiskTag // instanceof handles nulls
                && tagName.equals(((RiskTag) other).tagName)); // state check
    }

}
