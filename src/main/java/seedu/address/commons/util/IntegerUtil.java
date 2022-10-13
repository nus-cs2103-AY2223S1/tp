package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

/**
 * Helper functions for handling Integers.
 */
public class IntegerUtil {
    /**
     * Converts {@code target} of type {@code int} to {@code String}.
     */
    public static String getStringFromInt(int target) {
        requireNonNull(target);

        return String.valueOf(target);
    }
}
