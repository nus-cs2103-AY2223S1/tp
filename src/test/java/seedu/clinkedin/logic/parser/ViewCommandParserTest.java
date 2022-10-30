package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.ViewCommand;

public class ViewCommandParserTest {
    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Additional information given
        assertParseFailure(parser, "1 Test", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        ViewCommand expectedCommand = new ViewCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \t1 ", expectedCommand);
    }
}
