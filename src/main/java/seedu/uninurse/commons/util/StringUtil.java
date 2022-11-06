package seedu.uninurse.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {
    /**
     * Returns true if the sentence contains the word.
     * Ignores case and allows partial word match.
     * examples:
     * containsWordIgnoreCase("ABc def", "abc") == true
     * containsWordIgnoreCase("ABc def", "DEF") == true
     * containsWordIgnoreCase("ABc def", "AB") == true //partial word match
     *
     * @param sentence a string that cannot be null
     * @param word     a string that cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim().toLowerCase();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence.toLowerCase();
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        for (int i = 0; i < wordsInPreppedSentence.length; i++) {
            if (wordsInPreppedSentence[i].contains(preppedWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the sentence contains the word.
     * Ignores case and allows partial word match.
     * examples:
     * containsWordIgnoreCase("ABc def", "abc") == true
     * containsWordIgnoreCase("ABc def", "DEF") == true
     * containsWordIgnoreCase("ABc def", "AB") == true //partial word match
     * containsWordIgnoreCase("ABc def", "c d") == true //multiple word match
     *
     * @param sentence a string that cannot be null
     * @param word     a string that cannot be null
     */
    public static boolean containsIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);
        return sentence.trim().toLowerCase().contains(word.trim().toLowerCase());
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw;
    }

    /**
     * Returns true if the given string represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., Integer.MAX_VALUE
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @throws NullPointerException if string is null.
     */
    public static boolean isNonZeroUnsignedInteger(String string) {
        requireNonNull(string);

        try {
            int value = Integer.parseInt(string);
            return value > 0 && !string.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
