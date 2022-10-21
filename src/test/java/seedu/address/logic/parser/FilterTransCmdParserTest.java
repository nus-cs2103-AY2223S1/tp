package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterTransCommand;

class FilterTransCmdParserTest {

    private FilterTransCmdParser parser = new FilterTransCmdParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTransCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTransCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "buyyy", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTransCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "selll", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTransCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "123", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTransCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "123abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTransCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "---", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTransCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_success() {
        FilterTransCommand expectedOutput = new FilterTransCommand(true);
        assertParseSuccess(parser, "buy", expectedOutput);
        assertParseSuccess(parser, "bUy", expectedOutput);
        assertParseSuccess(parser, "buY", expectedOutput);
        assertParseSuccess(parser, "Buy", expectedOutput);
        assertParseSuccess(parser, "BUY", expectedOutput);
        assertParseSuccess(parser, "BuY", expectedOutput);

        FilterTransCommand expectedOutput2 = new FilterTransCommand(false);
        assertParseSuccess(parser, "sEll", expectedOutput2);
        assertParseSuccess(parser, "sell", expectedOutput2);
        assertParseSuccess(parser, "SELL", expectedOutput2);
        assertParseSuccess(parser, "SeLl", expectedOutput2);
        assertParseSuccess(parser, "seLL", expectedOutput2);
        assertParseSuccess(parser, "Sell", expectedOutput2);
    }

}
