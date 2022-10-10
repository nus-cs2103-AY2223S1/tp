package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class InternshipRole {
    public static final String MESSAGE_CONSTRAINTS =
            "Roles should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String roleName;

    /**
     * Constructs a {@code Role}.
     *
     * @param name A valid role name.
     */
    public InternshipRole(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        roleName = name;
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return roleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipRole // instanceof handles nulls
                && roleName.equals(((InternshipRole) other).roleName)); // state check
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }
}
