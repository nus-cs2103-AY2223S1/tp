package seedu.rc4hdb.model.venues;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Venue's name in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidVenueName(String)}
 */
public class VenueName extends StringField {

    public static final String MESSAGE_CONSTRAINTS =
            "Venue names should only contain alphanumeric characters and spaces, and it should not be blank.\n"
                    + "Venue names are also not case sensitive.";

    /**
     * The first character of the venue name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Constructs a {@code VenueName}.
     *
     * @param venueName A valid name string.
     */
    public VenueName(String venueName) {
        super(venueName);
        checkArgument(isValidVenueName(venueName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid venue name.
     */
    public static boolean isValidVenueName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || ((other instanceof VenueName)
                && value.equalsIgnoreCase(((VenueName) other).value));
    }

}
