package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Server in the address book.
 */
public class Server {

    private static final String VALIDATION_REGEX =
            "^.+@.+";

    private static final String SERVER_CONSTRAINTS =
            "Minecraft server input follows the format server_name@server_address.";

    private final String serverName;

    /**
     * Constructs a {@code Server}.
     * @param serverName A valid server name.
     */
    public Server(String serverName) {
        requireNonNull(serverName);
        checkArgument(isValidServerName(serverName), SERVER_CONSTRAINTS);
        this.serverName = serverName;
    }

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
     * Returns hashcode for purpose of the equals method.
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
