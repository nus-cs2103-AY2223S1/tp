package seedu.address.model.person;
//@@author Vshnv2001
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Software Engineer's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role implements Comparable<Role> {
    public static final String MESSAGE_CONSTRAINTS =
        "Roles should \n"
        + "1. contain only alphanumeric characters and spaces\n"
        + "2. start with alphabet\n"
        + "3. not empty";

    public static final String VALIDATION_REGEX = "[a-zA-z][a-zA-Z\\d ]*";
    public final String role;

    /**
     * Constructs an {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        this.role = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String roleString) {
        return roleString.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(Role o) {
        return role.compareToIgnoreCase(o.role);
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
