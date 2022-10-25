package eatwhere.foodguide.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eatwhere.foodguide.logic.commands.Command;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser<? extends Command> parser, String userInput,
            Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid userInput.", e);
        } catch (DisplayCommandHelpException e) {
            throw new AssertionError("A DisplayCommandHelpException was unexpectedly thrown.");
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser<? extends Command> parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        } catch (DisplayCommandHelpException e) {
            throw new AssertionError("The expected ParseException was not thrown.");
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} results in a command help
     * being displayed with message equal to {@code expectedMessage}.
     */
    public static void assertParseDisplayCommandHelp(Parser<? extends Command> parser,
                                                     String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected DisplayCommandHelpException was not thrown.");
        } catch (DisplayCommandHelpException e) {
            assertEquals(expectedMessage, e.getMessage());
        } catch (ParseException e) {
            throw new AssertionError("The expected DisplayCommandHelpException was not thrown.");
        }
    }
}
