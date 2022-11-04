package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortMeetingsCommand;

public class SortMeetingsCommandParserTest {
    private SortMeetingsCommandParser parser = new SortMeetingsCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortMeetingsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "invalid", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortMeetingsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "ASc desc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortMeetingsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "ascc descc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortMeetingsCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_validArgs_returnsFindMeetingCommand() {
        // no leading and trailing whitespaces
        SortMeetingsCommand expectedSortMeetingCommandDescription =
                new SortMeetingsCommand(true);
        assertParseSuccess(parser, "asc", expectedSortMeetingCommandDescription);
        // leading/trailing whitespaces
        assertParseSuccess(parser, " \t  asc \t \n", expectedSortMeetingCommandDescription);
        // testing case-sensitive
        assertParseSuccess(parser, " \t  AsC \t \n", expectedSortMeetingCommandDescription);

        // testing descending
        SortMeetingsCommand expectedSortMeetingCommandDescriptionDesc =
                new SortMeetingsCommand(false);
        assertParseSuccess(parser, "desc", expectedSortMeetingCommandDescriptionDesc);
        assertParseSuccess(parser, "DeSc", expectedSortMeetingCommandDescriptionDesc);

    }
}
