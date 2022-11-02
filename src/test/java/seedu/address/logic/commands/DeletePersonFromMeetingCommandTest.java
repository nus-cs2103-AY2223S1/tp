package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class DeletePersonFromMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());

    @Test
    public void equals() {
        DeletePersonFromMeetingCommand firstCommand = new DeletePersonFromMeetingCommand("test1");
        DeletePersonFromMeetingCommand secondCommand = new DeletePersonFromMeetingCommand("test2");

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeletePersonFromMeetingCommand firstCommandCopy = new DeletePersonFromMeetingCommand("test1");
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_delete_onePerson() {
        String expectedMessage = DeletePersonFromMeetingCommand.MESSAGE_DELETE_PEOPLE_TO_MEETING_SUCCESS;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("1; Carl");
        assertCommandSuccess(command, model, expectedMessage);
        assertEquals(Arrays.asList(ALICE), model.getMeetingList().getMeetingList().get(0).getArrayListPersonToMeet());
    }

    @Test
    public void execute_delete_twoPersons() {
        String expectedMessage = DeletePersonFromMeetingCommand.MESSAGE_DELETE_PEOPLE_TO_MEETING_SUCCESS;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("2; Alice, Elle");
        assertCommandSuccess(command, model, expectedMessage);
        assertEquals(Arrays.asList(FIONA), model.getMeetingList().getMeetingList().get(1).getArrayListPersonToMeet());
    }

    @Test
    public void execute_delete_nonExistentPerson() {
        String expectedMessage = String.format(PersonNotFoundException.PERSON_NOT_FOUND, "test") + "\n"
            + DeletePersonFromMeetingCommand.MESSAGE_DELETE_PEOPLE_NOT_FOUND;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("1; test");
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_delete_moreThanMeetingSize() {
        String expectedMessage = Messages.MESSAGE_INVALID_MEETING_ONLY_ONE_LEFT;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("1; Alice, Carl");
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_delete_samePerson() {
        String expectedMessage = Messages.MESSAGE_INVALID_DUPLICATE_NAMES;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("2; Alice, Alice");
        assertCommandFailure(command, model, expectedMessage);
    }
}
