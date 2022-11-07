package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UndoCommand;

public class UndoCommandParserTest {
    private UndoCommandParser parser = new UndoCommandParser();

    @Test
    public void parse_validArgs_returnsUndoCommand() {
        assertParseFailure(parser, " 1", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 2", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 3", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 4", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 5", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 6", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 7", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 8", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 9", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 10", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 11", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 12", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 13", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 14", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 15", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 16", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 17", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 18", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 19", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 20", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 21", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 22", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 23", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 24", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 25", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 26", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 27", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 28", UndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " 29", UndoCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, "", new UndoCommand());
    }
}
