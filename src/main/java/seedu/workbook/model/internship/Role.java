package seedu.workbook.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's role in WorkBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    // CHECKSTYLE.OFF: LineLength
    public static final String MESSAGE_CONSTRAINTS = "Role titles should only contain alphanumeric characters and spaces, and it should not be blank";
    // CHECKSTYLE.ON: LineLength

    /*
     * The first character of the Role must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid Role title.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        String modifiedRole = toUpperCase(role);
        value = modifiedRole;
    }

    /**
     * Returns true if a given string is a valid Role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                        && value.equals(((Role) other).value)); // state check
    }

    public String toUpperCase(String roleName) {
        char[] chars = roleName.toLowerCase().toCharArray();
        boolean isNotWhitespace = false;
        for (int i = 0; i < chars.length; i++) {
            if (!isNotWhitespace && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                isNotWhitespace = true;
            }
            else if (Character.isWhitespace(chars[i])) {
                isNotWhitespace = false;
            }
        }
        return String.valueOf(chars);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
