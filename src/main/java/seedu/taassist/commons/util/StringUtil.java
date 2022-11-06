package seedu.taassist.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Helper functions for handling strings.
 */
public class StringUtil<T> {

    /**
     * Returns true if the {@code sentence} contains the {@code word} ignoring case.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == true
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        return sentence.toLowerCase().contains(preppedWord.toLowerCase());
    }

    /**
     * Compares two strings case insensitively to see if they are equal.
     */
    public static boolean caseInsensitiveEquals(String string1, String string2) {
        return string1.toLowerCase().equals(string2.toLowerCase());
    }

    /**
     * Capitalises the first letter of each word in the trimmed {@code str} and sets the remaining characters
     * to lowercase.
     */
    public static String capitalise(String str) {
        requireNonNull(str);
        String trimmedString = str.trim();
        if (trimmedString.length() <= 1) {
            return trimmedString.toUpperCase();
        }
        return Arrays.stream(trimmedString.toLowerCase().split(" "))
                .map(w -> w.substring(0, 1).toUpperCase() + w.substring(1))
                .collect(Collectors.joining(" "));
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Joins the string representation of each object in {@code objects} with a comma.
     */
    public static <T> String commaSeparate(Collection<? extends T> objects, Function<T, String> fn) {
        return objects.stream().map(fn).collect(Collectors.joining(", "));
    }
}
