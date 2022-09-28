package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Social {

    public static final String MESSAGE_CONSTRAINTS =
            "Social handles can take any values, and it should not be blank";

    /*
     * The first character of the social must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String fullHandle;

    /**
     * Constructs a {@code Social}.
     *
     * @param handle A valid social media handle.
     */

    public Social(String handle, Platform platform) {
        requireNonNull(handle);
        requireNonNull(platform);
        checkArgument(isValidName(handle), MESSAGE_CONSTRAINTS);
        fullHandle = handle;
    }

    public Social(String handle) {
        requireNonNull(handle);
        checkArgument(isValidName(handle), MESSAGE_CONSTRAINTS);
        fullHandle = handle;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }




}
