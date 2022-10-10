package seedu.address.logic.parser;

import java.util.Arrays;

/**
 * Contains utility methods used to translate user inputs for the AddressBookParser class.
 * Currently, not fully implemented.
 */
public class ParserTranslator {
    public static String translate(String commandWord, String arguments) {
        String[] args = arguments.trim().split("\\s+");
        return Arrays.stream(args).reduce((x, y) -> x + " " + y).get().trim();
    }
}
