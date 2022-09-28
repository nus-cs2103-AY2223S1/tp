package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's minecraft username in the address book.
 */
public class MinecraftName {

    public static final String MESSAGE_CONSTRAINTS =
            "Minecraft names cannot have any spaces.";

    /*
     * Completely no whitespace in the name
     */
    public static final String VALIDATION_REGEX = "^\\S*$";

    public final String username;

    /**
     * Constructs a {@code Name}.
     *
     * @param username A valid Minecraft username.
     */
    public MinecraftName(String username) {
        requireNonNull(username);
        checkArgument(isValidMinecraftName(username), MESSAGE_CONSTRAINTS);
        this.username = username;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMinecraftName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MinecraftName // instanceof handles nulls
                && username.equals(((MinecraftName) other).username)); // state check
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

}
