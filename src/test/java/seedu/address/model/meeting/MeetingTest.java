package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DATE_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_MEETING2;
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

        // different meeting time -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withMeetingTime(VALID_MEETING_TIME_MEETING2).build();
        assertFalse(MEETING1.equals(editedMeeting1));

        // different client -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withClient(BENSON).build();
        assertFalse(MEETING1.equals(editedMeeting1));
    }
}
