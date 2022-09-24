package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Socials {

    public static final String MESSAGE_CONSTRAINTS =
            "Socials should not be blank and must be alphanumeric, with the exception of the underscore symbol: \"_\".";

    /*
     * The first character of the social media handle must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\w]*";

    public final String fullHandle;

    /**
     * Constructs a {@code Social}.
     *
     * @param handle A valid social media handle.
     */

    public Socials(String handle, Platform platform) {
        requireNonNull(handle);
        requireNonNull(platform);
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
