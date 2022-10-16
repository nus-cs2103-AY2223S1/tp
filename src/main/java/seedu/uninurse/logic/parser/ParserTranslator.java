package seedu.uninurse.logic.parser;

import java.util.Arrays;

/**
 * Contains utility methods used to translate user inputs for the UninurseBookParser class.
 * Currently, not fully implemented.
 */
public class ParserTranslator {
    /**
     * Replaces the given command word based on user preference settings (to be implemented
     * on a future version). Additionally, if there are two or more consecutive white spaces
     * in arguments, it will be trimmed to a single white space.
     *
     * @param commandWord the command word
     * @param arguments   the arguments for the command
     * @return the translated {@code String} of arguments.
     */
    public static String translate(String commandWord, String arguments) {
        String[] args = arguments.trim().split("\\s+");
        return Arrays.stream(args).reduce((x, y) -> x + " " + y).get().trim();
    }
}
