package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.model.meeting.MeetingContainsKeywordsPredicate;

public class FindMeetingCommandParserTest {

    private FindMeetingCommandParser parser = new FindMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindMeetingCommand() {
        // no leading and trailing whitespaces
        FindMeetingCommand expectedFindMeetingCommandDescription =
                new FindMeetingCommand(new MeetingContainsKeywordsPredicate(
                        Arrays.asList("CS2103", "CS1101"), FindMeetingCommand.GET_DESCRIPTION));
        assertParseSuccess(parser, "/named CS2103 CS1101", expectedFindMeetingCommandDescription);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, "/named  \n CS2103 \n \t CS1101  \t", expectedFindMeetingCommandDescription);


        //testing other types
        FindMeetingCommand expectedFindMeetingCommandPeople =
                new FindMeetingCommand(new MeetingContainsKeywordsPredicate(
                        Arrays.asList("John", "Alex"), FindMeetingCommand.GET_PEOPLE));
        assertParseSuccess(parser, "/with John Alex", expectedFindMeetingCommandPeople);
        assertParseSuccess(parser, "/with  \n John \n \t Alex  \t", expectedFindMeetingCommandPeople);


        FindMeetingCommand expectedFindMeetingCommandLocation =
                new FindMeetingCommand(new MeetingContainsKeywordsPredicate(
                        Arrays.asList("UTown", "COM1"), FindMeetingCommand.GET_LOCATION));
        assertParseSuccess(parser, "/at UTown COM1", expectedFindMeetingCommandLocation);
        assertParseSuccess(parser, "/at  \n UTown \n \t COM1  \t", expectedFindMeetingCommandLocation);
    }

}
