package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteMeetingCommand.MESSAGE_DELETE_MEETING_FAILURE_NOTFOUND;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class DeleteMeetingCommandTest {

    private static final String SECOND_PERSON_MEETINGTIME = "11-04-2022-11:00";
    private static final String NON_EXISTING_MEETINGTIME = "01-01-2000-00:00";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_deleteExistingMeeting_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withMeetingTimes().build();

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_SECOND_PERSON,
                new MeetingTime(SECOND_PERSON_MEETINGTIME));

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS,
                new MeetingTime(SECOND_PERSON_MEETINGTIME), editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNonExistingMeeting_throwsCommandException() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_SECOND_PERSON,
                new MeetingTime(NON_EXISTING_MEETINGTIME));

        String expectedMessage = String.format(MESSAGE_DELETE_MEETING_FAILURE_NOTFOUND,
                new MeetingTime(NON_EXISTING_MEETINGTIME), secondPerson);

        assertCommandFailure(deleteMeetingCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteMeetingCommand deleteMeetingCommand =
                new DeleteMeetingCommand(outOfBoundIndex, new MeetingTime(SECOND_PERSON_MEETINGTIME));

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void undo_commandExecuted_undoSuccessful() throws Exception {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        DeleteMeetingCommand deleteMeetingCommand =
                new DeleteMeetingCommand(INDEX_SECOND_PERSON, new MeetingTime(SECOND_PERSON_MEETINGTIME));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        deleteMeetingCommand.execute(expectedModel);
        CommandResult result = deleteMeetingCommand.undo(expectedModel);

        assertEquals(String.format(deleteMeetingCommand.MESSAGE_UNDO,
                new MeetingTime(SECOND_PERSON_MEETINGTIME), secondPerson), result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    void testEquals() {
        final DeleteMeetingCommand standardCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON,
                new MeetingTime(VALID_MEETING_TIME_AMY));
        // same values -> returns true
        DeleteMeetingCommand commandWithSameValues = new DeleteMeetingCommand(INDEX_FIRST_PERSON,
                new MeetingTime(VALID_MEETING_TIME_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteMeetingCommand(INDEX_SECOND_PERSON,
                new MeetingTime(VALID_MEETING_TIME_AMY))));
        // different description -> returns false
        assertFalse(standardCommand.equals(new DeleteMeetingCommand(INDEX_FIRST_PERSON,
                new MeetingTime(VALID_MEETING_TIME_BOB))));

    }
}
