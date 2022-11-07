package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_TIME;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.model.person.MeetingTime;

class DeleteMeetingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE);
    private final DeleteMeetingCommandParser parser = new DeleteMeetingCommandParser();

    @Test
    public void parse_oneMeeting_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + MEETING_TIME_DESC_AMY;

        DeleteMeetingCommand expectedCommand =
                new DeleteMeetingCommand(targetIndex, new MeetingTime(VALID_MEETING_TIME_AMY));
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
