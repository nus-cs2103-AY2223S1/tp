package seedu.boba.logic.parser;

import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.boba.commons.core.Messages;
import seedu.boba.logic.commands.RedoCommand;

public class RedoCommandParserTest {

    private final RedoCommandParser parser = new RedoCommandParser();

    @Test
    public void parse_validArgs_returnsRedoCommand() {
        assertParseSuccess(parser, "", new RedoCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "testing123",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));
    }
}
