package soconnect.logic.parser;

import soconnect.logic.commands.Command;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException If {@code userInput} does not conform the expected format.
     */
    T parse(String userInput) throws ParseException;
}
