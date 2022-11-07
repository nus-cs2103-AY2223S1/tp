package seedu.uninurse.logic.parser;

import seedu.uninurse.logic.commands.Command;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a Command.
 * @param <T> The type of the command.
 */
public interface Parser<T extends Command> {
    /**
     * Parses the given user input into a command.
     *
     * @param userInput The given user input to be parsed.
     * @return the resulting Command.
     * @throws ParseException if user input does not conform to the expected format
     */
    T parse(String userInput) throws ParseException;
}
