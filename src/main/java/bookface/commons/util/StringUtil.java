package bookface.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the entire {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean matchesWholeWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        AppUtil.checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        AppUtil.checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter"
                + " should be a single word");

        String[] wordsInPreppedSentence = sentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word} partially.
     *   Ignores case, but a full word match is not required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "ab") == true
     *       containsWordIgnoreCase("ABc def", "DE") == true
     *       containsWordIgnoreCase("ABc def", "Ac") == false
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsPartialWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        AppUtil.checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        AppUtil.checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter"
                + "should be a single word");

        String[] wordsInPreppedSentence = sentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(keyword -> containsIgnoreCase(keyword, word));
    }

    //@@author parth-io-reused
    // referenced from https://stackoverflow.com/a/14018549/13742805
    /**
     * Returns true if the {@code stringToSearchIn} contains the {@code keyword} partially.
     *   Ignores case, but a full match is not required.
     * @param stringToSearchIn cannot be null
     * @param keyword cannot be null
     */
    private static boolean containsIgnoreCase(String stringToSearchIn, String keyword) {
        if (keyword == null || stringToSearchIn == null) {
            return false;
        }
        final int length = keyword.length();
        if (length == 0) {
            return true;
        }
        for (int i = stringToSearchIn.length() - length; i >= 0; i--) {
            if (stringToSearchIn.regionMatches(true, i, keyword, 0, length)) {
                return true;
            }
        }
        return false;
    }
    //@@author

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
}
