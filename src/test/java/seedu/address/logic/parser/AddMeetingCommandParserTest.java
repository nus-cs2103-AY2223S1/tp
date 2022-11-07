package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_TIME;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.person.MeetingTime;

class AddMeetingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_oneMeeting_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + MEETING_TIME_DESC_AMY;

        Set<MeetingTime> newMeetingTimes = new HashSet<>();
        newMeetingTimes.add(new MeetingTime(VALID_MEETING_TIME_AMY));

        AddMeetingCommand expectedCommand = new AddMeetingCommand(targetIndex, newMeetingTimes);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleMeetings_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + MEETING_TIME_DESC_AMY + MEETING_TIME_DESC_BOB;

        Set<MeetingTime> newMeetingTimes = new HashSet<>();
        newMeetingTimes.add(new MeetingTime(VALID_MEETING_TIME_AMY));
        newMeetingTimes.add(new MeetingTime(VALID_MEETING_TIME_BOB));

        AddMeetingCommand expectedCommand = new AddMeetingCommand(targetIndex, newMeetingTimes);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, MEETING_TIME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no prefix
        assertParseFailure(parser, "1" + VALID_MEETING_TIME_AMY, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_MEETING_TIME, MeetingTime.MESSAGE_CONSTRAINTS);
    }
}
