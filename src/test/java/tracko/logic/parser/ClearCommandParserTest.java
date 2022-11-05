package tracko.logic.parser;

import static org.testng.AssertJUnit.assertEquals;
import static tracko.logic.parser.ClearCommandParser.INCORRECT_USER_INPUT_FORMAT;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.ClearCommand;
import tracko.logic.parser.exceptions.ParseException;

public class ClearCommandParserTest {
    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parseInitial_inputsIgnored_success() {

        // no input
        assertParseSuccess(parser, "", new ClearCommand());

        // whitespace only input
        assertParseSuccess(parser, " ", new ClearCommand());

        // string input
        assertParseSuccess(parser, "hello", new ClearCommand());

        // special character input
        assertParseSuccess(parser, "h@lo!", new ClearCommand());
    }

    @Test
    public void parseAndUpdate_validConfirm_success() {

        ClearCommand expectedCommand = new ClearCommand();
        expectedCommand.setAwaitingInput(false);

        try {
            ClearCommand command = parser.parseAndUpdate("confirm", new ClearCommand());
            assertEquals(command, expectedCommand);

        } catch (ParseException e) {
            throw new AssertionError("Unexpected ParseException thrown for confirmation");
        }
    }

    @Test
    public void parseAndUpdate_validCancel_success() {

        ClearCommand expectedCommand = new ClearCommand();
        expectedCommand.setAwaitingInput(false);
        expectedCommand.cancel();

        try {
            ClearCommand command = parser.parseAndUpdate("cancel", new ClearCommand());
            assertEquals(command, expectedCommand);

        } catch (ParseException e) {
            throw new AssertionError("Unexpected ParseException thrown for cancellation");
        }
    }

    @Test
    public void parserAndUpdate_invalidEmptyInput_failure() {

        String expectedMessage = INCORRECT_USER_INPUT_FORMAT;

        try {
            // empty user input
            parser.parseAndUpdate("", new ClearCommand());
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void parserAndUpdate_invalidStringInput_failure() {

        String expectedMessage = INCORRECT_USER_INPUT_FORMAT;

        try {
            // unrecognised string input
            parser.parseAndUpdate("Confirm", new ClearCommand());
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }

        try {
            // unrecognised string input
            parser.parseAndUpdate("c@ncel", new ClearCommand());
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
