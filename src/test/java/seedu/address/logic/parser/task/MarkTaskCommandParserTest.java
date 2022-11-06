package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAMMATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.MarkTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the MarkTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the MarkTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class MarkTaskCommandParserTest {

    private MarkTaskCommandParser parser = new MarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new MarkTaskCommand(INDEX_FIRST_TEAMMATE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE));
    }
}
