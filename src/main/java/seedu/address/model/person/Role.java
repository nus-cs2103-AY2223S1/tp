package seedu.address.model.person;

/**
 * Represents a Software Engineer's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {
    public static final String MESSAGE_CONSTRAINTS =
        "Roles should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String role;

    /**
     * Constructs an {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        this.role = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String roleString) {
        return roleString.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.role;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && this.role.equals(((Role) other).role)); // state check
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }

}
