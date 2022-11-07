package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RedoCommand;

public class RedoCommandParserTest {
    private RedoCommandParser parser = new RedoCommandParser();

    @Test
    public void parse_validArgs_returnsRedoCommand() {
        assertParseFailure(parser, " 1", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 2", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 3", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 4", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 5", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 6", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 7", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 8", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 9", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 10", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 11", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 12", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 13", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 14", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 15", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 16", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 17", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 18", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 19", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 20", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 21", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 22", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 23", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 24", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 25", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 26", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 27", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 28", RedoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 29", RedoCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, "", new RedoCommand());
    }
}

