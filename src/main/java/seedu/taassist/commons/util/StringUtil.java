package seedu.taassist.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Contains helper functions for handling strings.
 */
public class StringUtil<T> {

    /**
     * Returns true if the {@code sentence} contains the {@code word} ignoring case.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == true
     *       </pre>
     *
     * @param sentence Non-null sentence.
     * @param word Non-null and non-empty word. Must be a single word.
     * @return True if the {@code sentence} contains the {@code word} ignoring case.
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
     *
     * @param string1 String involved in comparison.
     * @param string2 String involved in comparison.
     * @return True if both strings are equivalent (case insensitively).
     */
    public static boolean caseInsensitiveEquals(String string1, String string2) {
        return string1.toLowerCase().equals(string2.toLowerCase());
    }

    /**
     * Returns a detailed message of the Throwable {@code t}, including the stack trace.
     *
     * @param t Throwable object.
     * @return Detailed message of the Throwable {@code t}.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer.
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @param s String representing an unsigned integer.
     * @return True if {@code s} represents a non-zero unsigned integer.
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
     *
     * @param objects Objects to display their string representation.
     * @param fn Function that returns the string representation of each object. This is useful for cases where you do
     *           not want to use the (default) string representation from the {@code toString} method.
     * @param <T> Type of object in {@code objects}.
     * @return String representation of each object in {@code objects} separated by commas.
     */
    public static <T> String commaSeparate(Collection<? extends T> objects, Function<T, String> fn) {
        return objects.stream().map(fn).collect(Collectors.joining(", "));
    }
}
