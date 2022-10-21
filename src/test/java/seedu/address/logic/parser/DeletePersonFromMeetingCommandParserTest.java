package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeletePersonFromMeetingCommand;

/**
 * Copied over from DeleteCommandParserTest:
 *
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePersonFromMeetingCommandParserTest {

    private DeletePersonFromMeetingCommandParser parser = new DeletePersonFromMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsCreateMeetingCommand() {
        assertParseSuccess(parser, "  0; Alice  ",
                new DeletePersonFromMeetingCommand("0; Alice"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonFromMeetingCommand.MESSAGE_USAGE));
    }
}
