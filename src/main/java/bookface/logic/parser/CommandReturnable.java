package bookface.logic.parser;

import bookface.logic.commands.Command;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An interface to ensure that implementing classes can run the {@code ParserFunction}
 */
public interface CommandReturnable {
    /**
     * Runs the parse function of the enum type
     * @param userInput The user input to be parsed
     * @return A {@code Command} representing the user command
     * @throws ParseException If there are errors parsing
     */
    Command runParseFunction(String userInput) throws ParseException;
}
