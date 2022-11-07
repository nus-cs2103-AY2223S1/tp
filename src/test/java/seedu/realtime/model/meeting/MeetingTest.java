package seedu.realtime.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_DATETIME_2;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_TAG_CHILDREN;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_TAG_CONTRACT;
import static seedu.realtime.testutil.Assert.assertThrows;
import static seedu.realtime.testutil.TypicalMeetings.ALICE;
import static seedu.realtime.testutil.TypicalMeetings.BOB;

import org.junit.jupiter.api.Test;

import seedu.realtime.testutil.MeetingBuilder;


public class MeetingTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Meeting meeting = new MeetingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> meeting.getTags().remove(0));
    }

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(ALICE.isSameMeeting(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameMeeting(null));

        // same address id, all other attributes different -> returns false
        Meeting editedAlice = new MeetingBuilder(ALICE)
                .withClient(VALID_NAME_BOB)
                .withDateTime(VALID_DATETIME_2)
                .withTags(VALID_TAG_CONTRACT)
                .build();
        assertFalse(ALICE.isSameMeeting(editedAlice));

        // different address id, all other attributes same -> returns false
        editedAlice = new MeetingBuilder(ALICE).withListing(VALID_ID_BOB).build();
        assertFalse(ALICE.isSameMeeting(editedAlice));

        // different time -> returns false
        editedAlice = new MeetingBuilder(ALICE).withDateTime(VALID_DATETIME_2).build();
        assertFalse(ALICE.isSameMeeting(editedAlice));

        // different client -> returns false
        editedAlice = new MeetingBuilder(ALICE).withClient(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameMeeting(editedAlice));

        // different tags -> returns true
        editedAlice = new MeetingBuilder(ALICE).withTags(VALID_TAG_CONTRACT, VALID_TAG_CHILDREN).build();
        assertTrue(ALICE.isSameMeeting(editedAlice));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting aliceCopy = new MeetingBuilder(ALICE).build();
        assertTrue(ALICE.isSameMeeting(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different meeting -> returns false
        assertFalse(ALICE.equals(BOB));

        // different address id -> returns false
        Meeting editedAlice = new MeetingBuilder(ALICE).withListing(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different time -> returns false
        editedAlice = new MeetingBuilder(ALICE).withDateTime(VALID_DATETIME_2).build();
        assertFalse(ALICE.equals(editedAlice));

        // different client -> returns false
        editedAlice = new MeetingBuilder(ALICE).withClient(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new MeetingBuilder(ALICE).withTags(VALID_TAG_CONTRACT, VALID_TAG_CHILDREN).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
