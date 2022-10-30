package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.commands.SortMeetingsCommand;
import seedu.address.model.meeting.MeetingContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class SortMeetingsCommandParserTest {
    private SortMeetingsCommandParser parser = new SortMeetingsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortMeetingsCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_validArgs_returnsFindMeetingCommand() {
        // no leading and trailing whitespaces
        SortMeetingsCommand expectedSortMeetingCommandDescription =
                new SortMeetingsCommand(true);
        assertParseSuccess(parser, "sortmeetings asc", expectedSortMeetingCommandDescription);
    }
}
