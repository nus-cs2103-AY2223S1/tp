package longtimenosee.model.event;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's PersonName in the addressBook
 * Each event can only be "Tagged" to one person/client.
 * Guarantees: immutable; is valid as declared in {@link #isValidPersonName(String)}
 */
public class PersonName {

    public static final String MESSAGE_CONSTRAINTS =
            "Person's Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String personName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public PersonName(String name) {
        requireNonNull(name);
        checkArgument(isValidPersonName(name), MESSAGE_CONSTRAINTS);
        personName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPersonName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return personName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonName// instanceof handles nulls
                && personName.equals(((PersonName) other).personName)); // state check
    }

}


