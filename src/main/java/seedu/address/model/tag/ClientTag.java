package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ClientTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class ClientTag extends SpecialTag {

    public static final String MESSAGE_CONSTRAINTS = "Client tag name should be [POTENTIAL] or [CURRENT]";
    public static final String VALIDATION_REGEX = "(CURRENT|POTENTIAL)";
    public static final String CURRENT = "CURRENT";
    public static final String POTENTIAL = "POTENTIAL";

    private int type;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public ClientTag(String tagName) {
        super(tagName);
        checkArgument(isValidClientTagName(tagName), MESSAGE_CONSTRAINTS);
        // only setRisk after we are sure that tagName is valid
        this.type = setClient(tagName);
    }

    public int getType() {
        return this.type;
    }

    public int setClient(String tagName) {
        switch (tagName) {

        case CURRENT:
            return this.type = 1;

        case POTENTIAL:
            return this.type = 2;

        default:
            return this.type = 0;
        }
    }

    /**
     * check if tag is a risk tag
     * @param tag
     * @return true if its a risk tag, false if otherwise
     */
    public static boolean isClientTag(String tag) {
        return CURRENT.equalsIgnoreCase(tag)
                || POTENTIAL.equalsIgnoreCase(tag);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidClientTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientTag // instanceof handles nulls
                && tagName.equals(((ClientTag) other).tagName)); // state check
    }

}
