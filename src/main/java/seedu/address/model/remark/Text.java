package seedu.address.model.remark;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Remark's text in the text book.
 * Guarantees: immutable; is valid as declared in {@link #isValidText(String)}
 */
public class Text {

    public static final String MESSAGE_CONSTRAINTS = "Remark text can be any values, but it should not be blank";

    /*
     * The first character of the text must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Text}.
     *
     * @param value A valid text.
     */
    public Text(String value) {
        requireNonNull(value);
        checkArgument(isValidText(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid text.
     */
    public static boolean isValidText(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String trimmedValue = value.substring(0, Math.min(value.length(), 15));
        return trimmedValue == value
                ? trimmedValue
                : trimmedValue + "...";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Text // instanceof handles nulls
                && value.equals(((Text) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return this.value;
    }

}
