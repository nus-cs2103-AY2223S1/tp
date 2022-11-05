package seedu.application.logic.parser;

import seedu.application.logic.commands.Command;
import seedu.application.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    public static String MESSAGE_UNKNOWN_PREFIX_FOUND = "Unknown prefix detected in the command, please "
            + "check the message usage for the correct prefix\n";

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
