package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GradeProgressCommand;
import seedu.address.logic.commands.UnmarkCommand;

public class UnmarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);

    private UnmarkCommandParser parser = new UnmarkCommandParser();

    @Test
    public void parse_noFieldSpecified_failure() {
        String userInput = UnmarkCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }


    @Test
    public void parse_invalidField_failure() {
        String userInput = GradeProgressCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }
}
