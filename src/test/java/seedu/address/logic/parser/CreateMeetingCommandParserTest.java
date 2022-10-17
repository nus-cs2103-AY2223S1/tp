package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateMeetingCommand;

/**
 * Copied over from DeleteCommandParserTest:
 *
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class CreateMeetingCommandParserTest {

    private CreateMeetingCommandParser parser = new CreateMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsCreateMeetingCommand() {
        assertParseSuccess(parser, "  Alex }} Yu ;;; Study session ;;; 20-11-2022 1520 ;;; Central Library  ",
            new CreateMeetingCommand("Alex }} Yu ;;; Study session ;;; 20-11-2022 1520 ;;; Central Library"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateMeetingCommand.MESSAGE_USAGE));
    }
}
