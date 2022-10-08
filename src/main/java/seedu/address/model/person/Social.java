package seedu.address.model.person;

import seedu.address.logic.parser.exceptions.SocialNotFoundException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Social {

    public static final String MESSAGE_CONSTRAINTS =
            "Social media handles should be social_media@username.";

    /*
     * The first character of the social must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*@.*";

    public String handle;
    public Platform platform;

    /**
     * Constructs a {@code Social}.
     *
     * @param fullString A valid social media string, in the form of platform@handle
     */

    public Social(String fullString) {
        requireNonNull(fullString);
        checkArgument(isValidSocial(fullString), MESSAGE_CONSTRAINTS);
        this.handle = fullString.split("@")[0];
        try {
            this.platform = PlatformConverter.stringToPlatform(fullString.split("@")[1]);
        } catch (SocialNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs a {@code Social}.
     *
     * @param handle A valid social media handle
     * @param platform A valid social media platform
     */

    public Social(String handle, Platform platform) {
        requireNonNull(handle);
        requireNonNull(platform);
        checkArgument(isValidSocial(handle + "@" + platform), MESSAGE_CONSTRAINTS);
        this.handle = handle;
        this.platform = platform;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidSocial(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return this.platform + "@" + this.handle;
    }

}
