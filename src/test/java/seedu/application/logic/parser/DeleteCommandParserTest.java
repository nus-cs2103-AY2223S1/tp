package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.UNKNOWN_PREFIX;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unexpectedPrefix_failure() {

        // unexpected prefix behind valid input
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + UNKNOWN_PREFIX,
                Parser.MESSAGE_UNKNOWN_PREFIX_FOUND + DeleteCommand.MESSAGE_USAGE);

        // unexpected prefix after valid input
        assertParseFailure(parser, UNKNOWN_PREFIX + INDEX_FIRST_APPLICATION.getOneBased(),
                Parser.MESSAGE_UNKNOWN_PREFIX_FOUND + DeleteCommand.MESSAGE_USAGE);
    }
}
