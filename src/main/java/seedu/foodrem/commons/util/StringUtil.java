package seedu.foodrem.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /*
     * Alphanumeric characters, whitespaces and the following symbols []{}()-+*=.,_'"^$?@!#%&:;
     */
    private static final String VALIDATION_REGEX = "[A-Za-z0-9()\\[\\]{}\\-+*=.,_'\"^$?@!#%&:; ]*";

    private static final String SYMBOLS = "[]{}()-+*=.,_'\"^$?@!#%&:;";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS =
            "The %s should only contain alphanumeric characters, spaces and the following symbols ";

    /**
     * Returns {@code true} if a given string is a contains only valid characters.
     */
    public static boolean isValidString(String string) {
        return string.matches(StringUtil.VALIDATION_REGEX);
    }

    /*
     * Returns a message to a user if a certain field fails the validation check for valid characters.
     */
    public static String getInvalidCharactersMessage(String field) {
        if (field.isBlank()) {
            field = "field";
        }
        return String.format(MESSAGE_FOR_INVALID_CHARACTERS, field) + SYMBOLS;
    }

    /**
     * Returns {@code true} if the {@code sentence} contains the {@code word}.
     * Ignores case, but a full word match is required.
     * <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word     cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String[] wordsInPreppedSentence = sentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
            .anyMatch(preppedWord::equalsIgnoreCase);
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
     * Returns {@code true} if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return {@code false} for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        return isInteger(s) && Integer.parseInt(s) > 0;
    }

    /**
     * Returns {@code true} if {@code s} represents an integer
     * e.g. {@code Integer.MIN_VALUE} -3, -2, -1, 0, 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return {@code false} for any other non-null string input
     * e.g. empty string, "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isInteger(String s) {
        requireNonNull(s);

        try {
            Integer.parseInt(s);
            return !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Validates if a string is can be parsed into an index, and if the index is positive.
     *
     * @throws NullPointerException if {@code s} is null.
     */
    public static Index validateAndGetIndexFromString(String string, String commandUsage) {
        requireNonNull(string);
        requireNonNull(commandUsage);

        String trimmedArgument = string.trim();

        if (trimmedArgument.isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, commandUsage));
        }

        if (!StringUtil.isInteger(trimmedArgument)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, commandUsage));
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedArgument)) {
            throw new ParseException("The index of an item should be a positive number.");
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(trimmedArgument);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, commandUsage), pe);
        }
        return index;
    }
}
