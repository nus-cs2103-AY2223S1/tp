package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {
    private static final String MESSAGE_NOT_VALID_CAP = "The cap value to search for that you inputted is invalid! "
            + "Please input doubles only";

    /** @see #requireAllNonNull(Collection) */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }

    /**
     * Throws NullPointerException if {@code items} or any element of {@code items} is null.
     */
    public static void requireAllNonNull(Collection<?> items) {
        requireNonNull(items);
        items.forEach(Objects::requireNonNull);
    }

    /**
     * Returns true if {@code items} contain any elements that are non-null.
     */
    public static boolean isAnyNonNull(Object... items) {
        return items != null && Arrays.stream(items).anyMatch(Objects::nonNull);
    }

    /**
     * Iterates through a list of keywords that will be used to search across cap values
     * @throws ParseException
     */
    public static void checkCapKeywords(String[] keywords) throws ParseException {
        for (String keyword : keywords) {
            try {
                Double.parseDouble(keyword);
            } catch (NumberFormatException | NullPointerException e) {
                throw new ParseException(MESSAGE_NOT_VALID_CAP);
            }
        }
    }
}
