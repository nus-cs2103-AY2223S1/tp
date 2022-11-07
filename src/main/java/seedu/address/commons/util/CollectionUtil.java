package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {
    private static final String MESSAGE_NOT_VALID_CAP = "The cap value to search for that you inputted is invalid! "
            + "Please input numeric values only!";

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
     * Rounds all cap values to be searched for to 2 decimal places.
     * @param keywords
     * @return List of keywords rounded to 2 decimal places
     * @throws ParseException
     */
    public static List<String> roundCapKeywords(List<String> keywords) throws ParseException {
        List<String> roundedKeywords = new ArrayList<>(keywords.size());
        for (String keyword : keywords) {
            try {
                Double keywordValue = Double.valueOf(keyword);
                roundedKeywords.add(String.format("%.2f", keywordValue));
            } catch (NumberFormatException | NullPointerException e) {
                throw new ParseException(MESSAGE_NOT_VALID_CAP);
            }
        }
        return roundedKeywords;
    }
}
