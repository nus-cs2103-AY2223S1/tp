package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.SelectCommand;

public class SelectCommandParserTest {
    private final SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        // no leading and trailing whitespaces
        SelectCommand expectedSelectCommand = new SelectCommand(Index.fromZeroBased(0));
        assertParseSuccess(parser, "1", expectedSelectCommand);
    }
}
