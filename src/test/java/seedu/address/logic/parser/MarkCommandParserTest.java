package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;


import org.junit.jupiter.api.Test;


import seedu.address.logic.commands.GradeProgressCommand;
import seedu.address.logic.commands.MarkCommand;


public class MarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_noFieldSpecified_failure() {
        String userInput = MarkCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }


    @Test
    public void parse_invalidField_failure() {
        String userInput = GradeProgressCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative integer
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero integer
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

}
