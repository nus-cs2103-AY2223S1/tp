package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's server in the address book.
 */
public class Server {

    private static final String VALIDATION_REGEX =
            "^.+@.+";

    private static final String SERVER_CONSTRAINTS =
            "Minecraft server input follows the format server_name@server_address.";

    private final String serverName;

    /**
     * Constructs a {@code Server}.
     * @param serverName the server's name
     */
    public Server(String serverName) {
        requireNonNull(serverName);
        checkArgument(isValidServerName(serverName), SERVER_CONSTRAINTS);
        this.serverName = serverName;
    }

    /**
     * Returns true if a given string is a valid server name.
     *
     * @param test A string.
     * @return A boolean value.
     */
    public static boolean isValidServerName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getServerName() {
        return serverName;
    }

    public static String getServerConstraints() {
        return SERVER_CONSTRAINTS;
    }

    /**
     * Returns hashcode for purpose of the  {@link #equals(Object)} method.
     * @return The hashcode of the String representation of the object.
     */
    @Override
    public int hashCode() {
        return this.getServerName().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return this == other //short circuit if the same object
                || (other instanceof Server //instanceof handles null
                && serverName.equals(((Server) other).getServerName())); //state check
    }

    @Override
    public String toString() {
        return serverName;
    }
}
