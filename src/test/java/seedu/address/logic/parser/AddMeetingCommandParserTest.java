package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MEETING1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;

public class AddMeetingCommandParserTest {
    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = MEETING1;

        // whitespace only preamble
        assertParseSuccess(parser, " " + PREFIX_INDEX + "1" + MEETING_DESC_MEETING1,
                new AddMeetingCommand(INDEX_FIRST_ELEMENT, expectedMeeting));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing index prefix
        assertParseFailure(parser, " " + "1" + " " + MEETING_DESC_MEETING1,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + " " + VALID_START_TIME_MEETING1
                        + " " + PREFIX_END_TIME + VALID_END_TIME_MEETING1
                        + " " + PREFIX_DATE + VALID_DATE_MEETING1
                        + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + " " + PREFIX_START_TIME + VALID_START_TIME_MEETING1
                        + " " + VALID_END_TIME_MEETING1
                        + " " + PREFIX_DATE + VALID_DATE_MEETING1
                        + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + " " + PREFIX_START_TIME + VALID_START_TIME_MEETING1
                        + " " + PREFIX_END_TIME + VALID_END_TIME_MEETING1
                        + " " + VALID_DATE_MEETING1
                        + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1,
                expectedMessage);

        //missing description prefix
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + " " + PREFIX_START_TIME + VALID_START_TIME_MEETING1
                        + " " + PREFIX_END_TIME + VALID_END_TIME_MEETING1
                        + " " + PREFIX_DATE + VALID_DATE_MEETING1
                        + " " + VALID_DESCRIPTION_MEETING1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " " + "1" + " " + PREFIX_START_TIME + VALID_START_TIME_MEETING1
                        + " " + VALID_END_TIME_MEETING1
                        + " " + VALID_DATE_MEETING1
                        + " " + VALID_DESCRIPTION_MEETING1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start time
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + INVALID_START_TIME_DESC
                        + " " + PREFIX_END_TIME + VALID_END_TIME_MEETING1
                        + " " + PREFIX_DATE + VALID_DATE_MEETING1
                        + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1,
                MeetingTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + " " + PREFIX_START_TIME + VALID_START_TIME_MEETING1
                        + INVALID_END_TIME_DESC
                        + " " + PREFIX_DATE + VALID_DATE_MEETING1
                        + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1,
                MeetingTime.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + " " + PREFIX_START_TIME + VALID_START_TIME_MEETING1
                        + " " + PREFIX_END_TIME + VALID_END_TIME_MEETING1
                        + INVALID_DATE_DESC
                        + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1,
                MeetingDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only second invalid value reported
        assertParseFailure(parser, " " + PREFIX_INDEX + "1" + INVALID_START_TIME_DESC
                        + " " + PREFIX_END_TIME + VALID_END_TIME_MEETING1
                        + INVALID_DATE_DESC
                        + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1,
                MeetingDate.MESSAGE_CONSTRAINTS);

    }
}
