package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Pattern;

/**
 * Represents a Module's Zoom Link in the address book
 * Guarantees: immutable; is valid as declared in {@link #isValidUrl(String)}
 */
public class ZoomLink {

    public static final String MESSAGE_CONSTRAINTS =
            "Zoom Link should be a valid URL";

    /*
     * Checks for whether the input by the user is a valid Url.
     */
    public static final String VALIDATION_REGEX =
            "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]"
                    + "{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";

    public final String zoomLink;

    /**
     * Constructs a {@code ZoomLink}.
     *
     * @param zoomLinkUrl A valid URL.
     */
    public ZoomLink(String zoomLinkUrl) {
        requireNonNull(zoomLinkUrl);
        checkArgument(isValidUrl(zoomLinkUrl), MESSAGE_CONSTRAINTS);
        zoomLink = zoomLinkUrl;
    }

    /**
     * Returns true if a given string is a valid Url.
     */
    public static boolean isValidUrl(String test) {
        return Pattern.compile(VALIDATION_REGEX)
                .matcher(test)
                .find();
    }


    @Override
    public String toString() {
        return zoomLink;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ZoomLink // instanceof handles nulls
                && zoomLink.equals(((ZoomLink) other).zoomLink)); // state check
    }

    @Override
    public int hashCode() {
        return zoomLink.hashCode();
    }
}
