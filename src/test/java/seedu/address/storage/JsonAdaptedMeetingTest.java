package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;

public class JsonAdaptedMeetingTest {

    private static final String INVALID_DESCRIPTION = "contains\nnewline";
    private static final String INVALID_MEETING_DATE = "010121234";
    private static final String INVALID_START_TIME = "0101a";
    private static final String INVALID_END_TIME = "12345";

    private static final String VALID_DESCRIPTION = "valid description";
    private static final String VALID_MEETING_DATE = "12122022";
    private static final String VALID_START_TIME = "0800";
    private static final String VALID_END_TIME = "0900";

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(MEETING1);
        assertTrue(MEETING1.equals(meeting.toModelType(ALICE)));
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(INVALID_DESCRIPTION,
                VALID_START_TIME, VALID_END_TIME, VALID_MEETING_DATE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(ALICE));
    }

    @Test
    public void toModelType_invalidMeetingDate_throwsIllegalValueException() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_DESCRIPTION,
                VALID_START_TIME, VALID_END_TIME, INVALID_MEETING_DATE);
        String expectedMessage = MeetingDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(ALICE));
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_DESCRIPTION,
                INVALID_START_TIME, VALID_END_TIME, VALID_MEETING_DATE);
        String expectedMessage = MeetingTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(ALICE));
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_DESCRIPTION,
                VALID_START_TIME, INVALID_END_TIME, VALID_MEETING_DATE);
        String expectedMessage = MeetingTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(ALICE));
    }

}
