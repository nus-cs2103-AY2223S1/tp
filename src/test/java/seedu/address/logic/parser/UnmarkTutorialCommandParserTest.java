package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIAL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkTutorialCommand;

public class UnmarkTutorialCommandParserTest {
    private UnmarkTutorialCommandParser parser = new UnmarkTutorialCommandParser();

    @Test
    public void parse_validArgs_returnsMarkTutorialCommand() {
        assertParseSuccess(parser, "1", new UnmarkTutorialCommand(INDEX_FIRST_TUTORIAL));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkTutorialCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs2_throwsParseException() {
        assertParseFailure(parser, "abc1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UnmarkTutorialCommand.MESSAGE_USAGE));
    }
}
