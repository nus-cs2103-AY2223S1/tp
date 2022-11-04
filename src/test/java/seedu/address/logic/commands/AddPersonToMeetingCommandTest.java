package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.exceptions.PersonNotFoundException;


public class AddPersonToMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());

    @Test
    public void equals() {
        AddPersonToMeetingCommand firstCommand = new AddPersonToMeetingCommand("test1");
        AddPersonToMeetingCommand secondCommand = new AddPersonToMeetingCommand("test2");

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        AddPersonToMeetingCommand firstCommandCopy = new AddPersonToMeetingCommand("test1");
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_add_onePerson() {
        String expectedMessage = AddPersonToMeetingCommand.MESSAGE_ADD_PEOPLE_TO_MEETING_SUCCESS;
        AddPersonToMeetingCommand command = new AddPersonToMeetingCommand("1; Benson");
        assertCommandSuccess(command, model, expectedMessage);
        assertEquals(Arrays.asList(ALICE, CARL, BENSON), model.getMeetingList().getMeetingList().get(0)
                        .getArrayListPersonToMeet());
    }

    @Test
    public void execute_add_twoPersons() {
        String expectedMessage = AddPersonToMeetingCommand.MESSAGE_ADD_PEOPLE_TO_MEETING_SUCCESS;
        AddPersonToMeetingCommand command = new AddPersonToMeetingCommand("2; Benson, Daniel");
        assertCommandSuccess(command, model, expectedMessage);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA, BENSON, DANIEL),
                model.getMeetingList().getMeetingList().get(1).getArrayListPersonToMeet());
    }

    @Test
    public void execute_add_existingPerson() {
        String expectedMessage = CreateMeetingCommand.DUPLICATE_PERSON_TO_MEET;
        AddPersonToMeetingCommand command = new AddPersonToMeetingCommand("1; Alice");
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_add_nonExistentPerson() {
        String expectedMessage = String.format(PersonNotFoundException.PERSON_NOT_FOUND, "test") + "\n"
            + CreateMeetingCommand.PERSON_NOT_FOUND;
        AddPersonToMeetingCommand command = new AddPersonToMeetingCommand("1; test");
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_add_duplicatePerson() {
        String expectedMessage = CreateMeetingCommand.DUPLICATE_PERSON_TO_MEET;
        AddPersonToMeetingCommand command = new AddPersonToMeetingCommand("1; Alice, Alice");
        assertCommandFailure(command, model, expectedMessage);
    }
}
