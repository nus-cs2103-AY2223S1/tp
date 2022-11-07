package seedu.uninurse.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {
    /** @see #requireAllNonNull(Collection) */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }

    /**
     * Ensures that the items are not null.
     *
     * @throws NullPointerException if items or any element of items is null.
     */
    public static void requireAllNonNull(Collection<?> items) {
        requireNonNull(items);
        items.forEach(Objects::requireNonNull);
    }

    /**
     * Returns true if items contain any elements that are non-null.
     */
    public static boolean isAnyNonNull(Object... items) {
        return items != null && Arrays.stream(items).anyMatch(Objects::nonNull);
    }
}
