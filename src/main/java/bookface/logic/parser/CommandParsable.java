package bookface.logic.parser;

import bookface.logic.commands.Command;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface CommandParsable<T extends Command> extends Parser<T> {
    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String command) throws ParseException;
}
