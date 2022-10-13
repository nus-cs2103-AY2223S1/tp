package nus.climods.logic.parser;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    // Validates user entered module codes at parsing stage.
    public static final Pattern MODULE_CODE_PATTERN = Pattern.compile("[A-Z]{0,3}\\d{4}");
    // Validates user flag, case insensitive
    public static final Pattern USER_FLAG_PATTERN = Pattern.compile("(?i)--user(?-i)");
    // Validates faculty code, case insensitive and matches between 2-3 chars
    public static final Pattern FACULTY_CODE_PATTERN = Pattern.compile("^[A-Za-z]{2,3}$");
    // TODO: Change this to a less hacky solution
    public static final String FAULTY_FACULTY_CODE = "zzz";
    /**
     * Returns List of whitespace-delimited arguments given arguments string supplied by the user
     * @param arguments String supplied by user as arguments after preamble
     * @return List of arguments provided
     */
    public static List<String> convertArgumentStringToList(String arguments) {
        List<String> res = ArgumentTokenizer
            .tokenize(arguments.trim(), new Prefix(""))
            .getAllValues(new Prefix(""));

        // When arguments is empty string once trimmed, res is a List with just one empty string
        // Return empty list to demarcate this case clearly
        if (res.size() == 1 && res.get(0).trim().equals("")) {
            return List.of();
        }

        return res;
    }

    /**
     * Returns String array of whitespace-delimited arguments given arguments string supplied by the user. This function
     * makes it more convenient to work with commons.cli which accepts a String array of arguments to parse
     *
     * @param arguments String supplied by user as arguments after preamble
     * @return String array of arguments provided
     */
    public static String[] convertArgumentStringToArray(String arguments) {
        return convertArgumentStringToList(arguments).toArray(new String[] {});
    }

    /**
     * Parses module code according to user input
     *
     * @param input Input string from user representing a module code
     * @return Empty Optional if input string does not pass initial validation check, else Optional of the entered
     *               String
     */
    public static Optional<String> parseModuleCode(String input) {
        if (!MODULE_CODE_PATTERN.matcher(input.trim()).find()) {
            return Optional.empty();
        }

        return Optional.of(input);
    }

    /**
     * Parse user flag according to user input
     * @param input Input string from user containing --user. Case insensitive
     * @return Empty optional if input string does not contain --user, else Optional of --user flag
     */
    public static Optional<Boolean> parseUserFlag(String input) {
        if (!USER_FLAG_PATTERN.matcher(input.trim()).find()) {
            return Optional.empty();
        }
        return Optional.of(Boolean.TRUE);
    }

    /**
     * Parse faculty code according to user input
     * @param input Input string from user containing faculty code
     * @return
     */
    public static Optional<String> parseFacultyCode(String input) {
        if (input.length() == 0) {
            return Optional.empty();
        }
        input = !FACULTY_CODE_PATTERN.matcher(input.trim()).find() ? FAULTY_FACULTY_CODE : input;
        return Optional.of(input);
    }
    // TODO: Remove addressbook code

}
