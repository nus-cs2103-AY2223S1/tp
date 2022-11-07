package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEETING_2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditMeetingDescriptor descriptorWithSameValues = new EditMeetingDescriptor(MEETING_1);
        assertTrue(MEETING_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(MEETING_1.equals(MEETING_1));

        // null -> returns false
        assertFalse(MEETING_1.equals(null));

        // different types -> returns false
        assertFalse(MEETING_1.equals(5));

        // different values -> returns false
        assertFalse(MEETING_1.equals(MEETING_2));

        // different name -> returns false
        EditMeetingDescriptor editedMeeting1 = new EditMeetingDescriptorBuilder(MEETING_1)
                .withDescription(VALID_NAME_MEETING_2).build();
        assertFalse(MEETING_1.equals(editedMeeting1));

        // different location -> returns false
        editedMeeting1 = new EditMeetingDescriptorBuilder(MEETING_1).withLocation(VALID_LOCATION_MEETING_2).build();
        assertFalse(MEETING_1.equals(editedMeeting1));

        // different date -> returns false
        editedMeeting1 = new EditMeetingDescriptorBuilder(MEETING_1).withDate(VALID_DATE_MEETING_2).build();
        assertFalse(MEETING_1.equals(editedMeeting1));
    }
}
