package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CommandParserTestUtil.assertParseFailure;
import static coydir.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import coydir.logic.commands.BatchAddCommand;

public class BatchAddCommandParserTest {

    private BatchAddCommandParser parser = new BatchAddCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "coydir.csv", new BatchAddCommand("coydir.csv"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchAddCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchAddCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchAddCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "coydir.xlsx",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchAddCommand.MESSAGE_USAGE));
    }
}
