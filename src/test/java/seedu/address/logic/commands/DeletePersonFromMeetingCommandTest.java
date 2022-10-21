package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.FIONA;

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
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("0; Carl");
        assertCommandSuccess(command, model, expectedMessage);
        assertEquals(Arrays.asList(ALICE),
                model.getMeetingList().getMeetingList().get(model.getMeetingList().getMeetingList().size() - 1)
                        .getArrayListPersonToMeet());
    }

    @Test
    public void execute_delete_twoPersons() {
        String expectedMessage = DeletePersonFromMeetingCommand.MESSAGE_DELETE_PEOPLE_TO_MEETING_SUCCESS;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("1; Alice, Elle");
        assertCommandSuccess(command, model, expectedMessage);
        assertEquals(Arrays.asList(FIONA),
                model.getMeetingList().getMeetingList().get(model.getMeetingList().getMeetingList().size() - 1)
                        .getArrayListPersonToMeet());
    }

    @Test
    public void execute_delete_nonExistentPerson() {
        String expectedMessage = CreateMeetingCommand.PERSON_NOT_FOUND;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("0; test");
        assertCommandSuccess(command, model, expectedMessage);
    }

    @Test
    public void execute_delete_more_than_meetingSize() {
        String expectedMessage = Messages.MESSAGE_INVALID_MEETING_ONLY_ONE_LEFT ;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("0; Alice, Carl");
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_delete_samePerson() {
        String expectedMessage = CreateMeetingCommand.PERSON_NOT_FOUND ;
        DeletePersonFromMeetingCommand command = new DeletePersonFromMeetingCommand("1; Alice, Alice");
        assertCommandFailure(command, model, expectedMessage);
    }
}
