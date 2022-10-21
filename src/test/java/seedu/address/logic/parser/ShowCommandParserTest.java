package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCommand;

public class ShowCommandParserTest {

    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsShowCommand() {
        // no leading and trailing whitespaces
        ShowCommand expectedShowCommand = new ShowCommand("Mon");
        assertParseSuccess(parser, "Mon", expectedShowCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Mon \n", expectedShowCommand);

    }

    @Test
    public void test_isValidArgument() {
        boolean actualOutput1 = ShowCommandParser.isValidArgument("tUe");
        assertTrue(actualOutput1 == (true));

        boolean actualOutput2 = ShowCommandParser.isValidArgument("random");
        assertFalse(actualOutput2 == (true));
    }
}
