package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.getTypicalMyInsuRec;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandTest {

    private Model model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null, null, null, null));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Meeting meetingToAdd = MEETING1;
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meetingToAdd);

        assertCommandFailure(addMeetingCommand, model, addMeetingCommand.MESSAGE_CONFLICTING_MEETING);
    }

    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() {
        Meeting meetingToAdd = MEETING1;
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(Index.fromOneBased(99), meetingToAdd);

        assertCommandFailure(addMeetingCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Meeting meeting1 = new MeetingBuilder().withDescription("meeting1").build();
        Meeting meeting2 = new MeetingBuilder().withDescription("meeting2").build();
        AddMeetingCommand addMeeting1Command = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meeting1);
        AddMeetingCommand addMeeting2Command = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meeting2);

        // same object -> returns true
        assertTrue(addMeeting1Command.equals(addMeeting1Command));

        // same values -> returns true
        AddMeetingCommand addAliceCommandCopy = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meeting1);
        assertTrue(addMeeting1Command.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addMeeting1Command.equals(1));

        // null -> returns false
        assertFalse(addMeeting1Command.equals(null));

        // different Meeting -> returns false
        assertFalse(addMeeting1Command.equals(addMeeting2Command));
    }
}
