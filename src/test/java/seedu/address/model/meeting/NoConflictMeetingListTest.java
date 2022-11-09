package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.MEETING2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.ConflictingMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.MeetingBuilder;

public class NoConflictMeetingListTest {

    private final NoConflictMeetingList noConflictMeetingList = new NoConflictMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noConflictMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(noConflictMeetingList.contains(MEETING1));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        noConflictMeetingList.add(MEETING1);
        assertTrue(noConflictMeetingList.contains(MEETING1));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noConflictMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsConflictingMeetingException() {
        noConflictMeetingList.add(MEETING1);
        assertThrows(ConflictingMeetingException.class, () -> noConflictMeetingList.add(MEETING1));
    }

    @Test
    public void contains_meetingWithSameIdentityFieldsInList_returnsTrue() {
        noConflictMeetingList.add(MEETING1);
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withDescription(VALID_DESCRIPTION_MEETING2)
                .build();
        assertTrue(noConflictMeetingList.contains(editedMeeting1));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noConflictMeetingList.setMeeting(null, MEETING1));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noConflictMeetingList.setMeeting(MEETING1, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsClientNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> noConflictMeetingList.setMeeting(MEETING1, MEETING1));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        noConflictMeetingList.add(MEETING1);
        noConflictMeetingList.setMeeting(MEETING1, MEETING1);
        NoConflictMeetingList expectedNoConflictMeetingList = new NoConflictMeetingList();
        expectedNoConflictMeetingList.add(MEETING1);
        assertEquals(expectedNoConflictMeetingList, noConflictMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        noConflictMeetingList.add(MEETING1);
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withDescription(VALID_DESCRIPTION_MEETING2)
                .build();
        noConflictMeetingList.setMeeting(MEETING1, editedMeeting1);
        NoConflictMeetingList expectedNoConflictMeetingList = new NoConflictMeetingList();
        expectedNoConflictMeetingList.add(editedMeeting1);
        assertEquals(expectedNoConflictMeetingList, noConflictMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        noConflictMeetingList.add(MEETING1);
        noConflictMeetingList.setMeeting(MEETING1, MEETING2);
        NoConflictMeetingList expectedNoConflictMeetingList = new NoConflictMeetingList();
        expectedNoConflictMeetingList.add(MEETING2);
        assertEquals(expectedNoConflictMeetingList, noConflictMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasConflictingTiming_throwsConflictingMeetingException() {
        noConflictMeetingList.add(MEETING1);
        noConflictMeetingList.add(MEETING2);
        assertThrows(ConflictingMeetingException.class, () -> noConflictMeetingList.setMeeting(MEETING1, MEETING2));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noConflictMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> noConflictMeetingList.remove(MEETING1));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        noConflictMeetingList.add(MEETING1);
        noConflictMeetingList.remove(MEETING1);
        NoConflictMeetingList expectedNoConflictMeetingList = new NoConflictMeetingList();
        assertEquals(expectedNoConflictMeetingList, noConflictMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noConflictMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_noConflictMeetingList_replacesOwnListWithProvidedNoConflictMeetingList() {
        noConflictMeetingList.add(MEETING1);
        NoConflictMeetingList expectedNoConflictMeetingList = new NoConflictMeetingList();
        expectedNoConflictMeetingList.add(MEETING2);
        noConflictMeetingList.setMeeting(expectedNoConflictMeetingList);
        assertEquals(expectedNoConflictMeetingList, noConflictMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noConflictMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        noConflictMeetingList.add(MEETING1);
        List<Meeting> meetingList = Collections.singletonList(MEETING2);
        noConflictMeetingList.setMeetings(meetingList);
        NoConflictMeetingList expectedNoConflictMeetingList = new NoConflictMeetingList();
        expectedNoConflictMeetingList.add(MEETING2);
        assertEquals(expectedNoConflictMeetingList, noConflictMeetingList);
    }

    @Test
    public void setMeetings_listWithConflictingMeetings_throwsConflictingMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(MEETING1, MEETING1);
        assertThrows(ConflictingMeetingException.class, () ->
                noConflictMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> noConflictMeetingList.asUnmodifiableObservableList().remove(0));
    }
}
