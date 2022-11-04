package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DATE_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DATE_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_MEETING2;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.MEETING2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    @Test
    public void equals() {
        // same values -> returns true
        Meeting meeting1Copy = new MeetingBuilder(MEETING1).build();
        assertTrue(MEETING1.equals(meeting1Copy));

        // same object -> returns true
        assertTrue(MEETING1.equals(MEETING1));

        // null -> returns false
        assertFalse(MEETING1.equals(null));

        // different type -> returns false
        assertFalse(MEETING1.equals(5));

        // different meeting -> returns false
        assertFalse(MEETING1.equals(MEETING2));

        // different description -> returns false
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withDescription(VALID_DESCRIPTION_MEETING2).build();
        assertFalse(MEETING1.equals(editedMeeting1));

        // different meeting date -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withMeetingDate(VALID_MEETING_DATE_MEETING2).build();
        assertFalse(MEETING1.equals(editedMeeting1));

        // different meeting time -> returns true
        editedMeeting1 = new MeetingBuilder(MEETING1).withMeetingStartTime(VALID_MEETING_TIME_MEETING2).build();
        assertTrue(MEETING1.equals(editedMeeting1));

        // different client -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withClient(BENSON).build();
        assertFalse(MEETING1.equals(editedMeeting1));
    }

    @Test
    public void getClientName_validMeeting_getsClientName() {
        Meeting meeting1 = new MeetingBuilder(MEETING1)
                .withDescription(VALID_DESCRIPTION_MEETING2)
                .withMeetingDate(VALID_MEETING_DATE_MEETING2)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING2)
                .withClient(BENSON)
                .build();
        assertEquals(meeting1.getClientName(), meeting1.getClient().getName());
    }

    @Test
    public void getClientPhone_validMeeting_getsClientPhone() {
        Meeting meeting1 = new MeetingBuilder(MEETING1)
                .withDescription(VALID_DESCRIPTION_MEETING2)
                .withMeetingDate(VALID_MEETING_DATE_MEETING2)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING2)
                .withClient(BENSON)
                .build();
        assertEquals(meeting1.getClientPhone(), meeting1.getClient().getPhone());
    }

    @Test
    public void willConflict_meetingWithDifferentTimeDate_false() {
        Meeting meeting1 = new MeetingBuilder(MEETING1)
                .withDescription(VALID_DESCRIPTION_MEETING1)
                .withMeetingDate(VALID_MEETING_DATE_MEETING1)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING1)
                .withClient(ALICE)
                .build();

        Meeting meeting2 = new MeetingBuilder(MEETING2)
                .withDescription(VALID_DESCRIPTION_MEETING2)
                .withMeetingDate(VALID_MEETING_DATE_MEETING2)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING2)
                .withClient(BENSON)
                .build();
        assertFalse(meeting1.willConflict(meeting2));
    }

    @Test
    public void willConflict_meetingWithSameTimeDate_true() {
        Meeting meeting1 = new MeetingBuilder(MEETING1)
                .withDescription(VALID_DESCRIPTION_MEETING1)
                .withMeetingDate(VALID_MEETING_DATE_MEETING1)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING1)
                .withClient(ALICE)
                .build();

        Meeting meeting2 = new MeetingBuilder(MEETING2)
                .withDescription(VALID_DESCRIPTION_MEETING2)
                .withMeetingDate(VALID_MEETING_DATE_MEETING1)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING1)
                .withClient(BENSON)
                .build();
        assertTrue(meeting1.willConflict(meeting2));
    }

    @Test
    public void willConflict_null_true() {
        Meeting meeting1 = new MeetingBuilder(MEETING1)
                .withDescription(VALID_DESCRIPTION_MEETING1)
                .withMeetingDate(VALID_MEETING_DATE_MEETING1)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING1)
                .withClient(ALICE)
                .build();

        Meeting meeting2 = null;
        assertTrue(meeting1.willConflict(meeting2));
    }

    @Test
    public void toString_meeting_true() {
        Meeting meeting1 = new MeetingBuilder(MEETING1)
                .withDescription(VALID_DESCRIPTION_MEETING1)
                .withMeetingDate(VALID_MEETING_DATE_MEETING1)
                .withMeetingStartTime(VALID_MEETING_TIME_MEETING1)
                .withClient(ALICE)
                .build();
        String expected = "Client: Alice Pauline; Description: meeting1; Date: 08012020; Start: 0720; End: 0820";
        assertEquals(meeting1.toString(), expected);
    }
}
