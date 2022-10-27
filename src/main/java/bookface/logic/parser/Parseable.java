package bookface.logic.parser;

import bookface.logic.commands.Command;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Represents a Parseable object that is able to parse a string into a {@code Command} of type {@code T}.
 */
public interface Parseable<T extends Command> {
    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String args) throws ParseException;
}
