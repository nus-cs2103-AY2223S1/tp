package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.commands.UnplanCommand;

public class UnplanCommandParserTest {
    private final UnplanCommandParser parser = new UnplanCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnplanCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnplanCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnplanCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        MultiIndex multiIndex = new MultiIndex();
        multiIndex.addIndex(Index.fromZeroBased(0));
        multiIndex.addIndex(Index.fromZeroBased(0));
        // no leading and trailing whitespaces
        UnplanCommand expectedUnplanCommand = new UnplanCommand(multiIndex);
        assertParseSuccess(parser, "1.1", expectedUnplanCommand);
    }
}
