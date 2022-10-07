package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Id {

    public static final String MESSAGE_CONSTRAINTS = "Id should only contain 3 digits and 1 character";
    public static final String VALIDATION_REGEX = "\\d{3}[a-zA-Z]";
    public final String value;

    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id.toUpperCase();
    }

    public static boolean isValidId(String test) {
        return test.length() == 4 && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Id
                && value.equals(((Id) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
