package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.ViewCommand;

public class ViewCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.COMMAND_WORD, ViewCommand.MESSAGE_USAGE);
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validIndex_success() {
        Index index = INDEX_FIRST_STUDENT;
        assertParseSuccess(parser, index.toString(), new ViewCommand(index));
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, INVALID_INDEX, MESSAGE_INVALID_FORMAT);
    }
}
