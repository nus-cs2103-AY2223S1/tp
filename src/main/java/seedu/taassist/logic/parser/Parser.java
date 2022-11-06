package seedu.taassist.logic.parser;

import seedu.taassist.logic.commands.Command;
import seedu.taassist.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput User input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    T parse(String userInput) throws ParseException;
}
