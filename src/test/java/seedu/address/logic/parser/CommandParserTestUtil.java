package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.profile.AddProfileCommandParser;
import seedu.address.logic.parser.profile.DeleteProfileCommandParser;
import seedu.address.logic.parser.profile.EditProfileCommandParser;
import seedu.address.logic.parser.profile.FindProfileCommandParser;
import seedu.address.logic.parser.profile.ViewProfilesCommandParser;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser<? extends Command> parser, String userInput, Command expectedCommand) {
        if (parser instanceof AddProfileCommandParser) {
            userInput = " -a " + userInput;
        } else if (parser instanceof EditProfileCommandParser) {
            userInput = " -e " + userInput;
        } else if (parser instanceof ViewProfilesCommandParser) {
            userInput = " -v " + userInput;
        } else if (parser instanceof DeleteProfileCommandParser) {
            userInput = " -d " + userInput;
        } else if (parser instanceof FindProfileCommandParser) {
            userInput = " -f " + userInput;
        }

        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser<? extends Command> parser, String userInput, String expectedMessage) {
        if (parser instanceof AddProfileCommandParser) {
            userInput = " -a " + userInput;
        } else if (parser instanceof EditProfileCommandParser) {
            userInput = " -e " + userInput;
        } else if (parser instanceof ViewProfilesCommandParser) {
            userInput = " -v " + userInput;
        } else if (parser instanceof DeleteProfileCommandParser) {
            userInput = " -d " + userInput;
        } else if (parser instanceof FindProfileCommandParser) {
            userInput = " -f " + userInput;
        }

        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
