package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMeetingCommand;

/**
 * Class to test DeleteMeetingCommandParser.java
 */
public class DeleteMeetingCommandParserTest {

    private DeleteMeetingCommandParser parser = new DeleteMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMeetingCommand() {
        assertParseSuccess(parser, "1", new DeleteMeetingCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_nonNumeralArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeNumberArgs_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_decimalNumberArgs_throwsParseException() {
        assertParseFailure(parser, "1.3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleArgs_throwsParseException() {
        assertParseFailure(parser, "1 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_indexZeroArgs_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteMeetingCommand.MESSAGE_USAGE));
    }

}
