package seedu.boba.logic.parser;

import seedu.boba.logic.commands.Command;
import seedu.boba.logic.parser.exceptions.ParseException;

/**
 * Represents a CommandParser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface CommandParser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
