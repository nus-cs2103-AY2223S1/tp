package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMeetingCommand}.
 */
public class DeleteMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getMeetingList(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_PERSON);

        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getMeetingList(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);
        showNoMeeting(expectedModel);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMeetingCommand deleteMeetingFirstCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON);
        DeleteMeetingCommand deleteMeetingSecondCommand = new DeleteMeetingCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteMeetingFirstCommand.equals(deleteMeetingFirstCommand));

        // same values -> returns true
        DeleteMeetingCommand deleteMeetingFirstCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteMeetingFirstCommand.equals(deleteMeetingFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteMeetingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteMeetingFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteMeetingFirstCommand.equals(deleteMeetingSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMeeting(Model model) {
        model.updateFilteredMeetingList(p -> false);

        assertTrue(model.getFilteredMeetingList().isEmpty());
    }
}
