package seedu.boba.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.boba.logic.commands.Command;
import seedu.boba.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code commandParser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(CommandParser<? extends Command> commandParser, String userInput,
                                          Command expectedCommand) {
        try {
            Command command = commandParser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code commandParser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(CommandParser<? extends Command> commandParser, String userInput, String expectedMessage) {
        try {
            commandParser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
