package seedu.guest.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
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

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code date}.
     *   Ignores hyphen and space, but a full date match is required.
     *   <br>examples:<pre>
     *       containsDateIgnoreHyphenIgnoreSpace("13/09/22 - 15/09/22", "13/09/22") == true
     *       containsDateIgnoreHyphenIgnoreSpace("13/09/22 - 15/09/22", "15/09/22") == true
     *       containsDateIgnoreHyphenIgnoreSpace("13/09/22 - 15/09/22", "14/09/22")
     *              == false //not a full date match
     *       containsDateIgnoreHyphenIgnoreSpace("13/09/22 - 15/09/22", "13/09")
     *              == false //not a full date match
     *       </pre>
     * @param sentence cannot be null
     * @param date cannot be null, cannot be empty, must be a single date
     */
    public static boolean containsDateIgnoreHyphenIgnoreSpace(String sentence, String date) {
        requireNonNull(sentence, date);

        String preppedDate = date.trim().replace("-", "");
        if (preppedDate.isEmpty()) {
            return false;
        }
        checkArgument(preppedDate.split("\\s+").length == 1, "Date parameter should be a single date");

        String preppedSentence = sentence.replace("-", "");
        String[] datesInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(datesInPreppedSentence)
                .anyMatch(preppedDate::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code isRoomClean}.
     *   Ignores case, but must be either 'yes', 'no', 'y' or 'n'.
     *   <br>examples:<pre>
     *       containsIsRoomCleanIgnoreCase("yes", "y") == true
     *       containsIsRoomCleanIgnoreCase("yes", "yes") == true
     *       containsIsRoomCleanIgnoreCase("yes", "yEs") == true
     *       containsIsRoomCleanIgnoreCase("no", "n") == true
     *       containsIsRoomCleanIgnoreCase("no", "no") == true
     *       containsIsRoomCleanIgnoreCase("no", "nO") == true
     *       containsIsRoomCleanIgnoreCase("yes", "yep") == false //not in the correct format
     *       containsIsRoomCleanIgnoreCase("no", "nope") == false //not in the correct format
     *       </pre>
     * @param sentence cannot be null
     * @param isRoomClean cannot be null, cannot be empty, must be a single isRoomClean
     */
    public static boolean containsIsRoomCleanIgnoreCase(String sentence, String isRoomClean) {
        requireNonNull(sentence);
        requireNonNull(isRoomClean);

        String preppedIsRoomClean = isRoomClean.trim();
        checkArgument(!preppedIsRoomClean.isEmpty(), "IsRoomClean parameter cannot be empty");
        checkArgument(preppedIsRoomClean.split("\\s+").length == 1,
                "IsRoomClean parameter should be a single IsRoomClean");
        if (preppedIsRoomClean.equals("y") || preppedIsRoomClean.equals("Y")) {
            preppedIsRoomClean = "yes";
        } else if (preppedIsRoomClean.equals("n") || preppedIsRoomClean.equals("N")) {
            preppedIsRoomClean = "no";
        }

        String preppedSentence = sentence;
        String[] isRoomCleansInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(isRoomCleansInPreppedSentence)
                .anyMatch(preppedIsRoomClean::equalsIgnoreCase);
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
}
