package seedu.address.model.server;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Server {

    private static final String VALIDATION_REGEX = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";

    private static final String SERVER_CONSTRAINTS =
            "Server address should only contain numbers and dots. " +
                    "There can only be a maximum of 4 dots in the address.";

    private final String serverName;


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
