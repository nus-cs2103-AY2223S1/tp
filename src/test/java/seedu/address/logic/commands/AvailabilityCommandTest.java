package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class AvailabilityCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validAvailabilityUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AvailabilityCommand availabilityCommand = new AvailabilityCommand(INDEX_FIRST_PERSON, "Unavailable");
        Person editedPerson = personToEdit;
        editedPerson.getPosition().setDetails("Unavailable");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);
        String expectedMessage = String.format(AvailabilityCommand.MESSAGE_EDIT_AVAILABILITY_SUCCESS, editedPerson);

        assertCommandSuccess(availabilityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexPerson_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AvailabilityCommand availabilityCommand = new AvailabilityCommand(outOfBoundIndex, "Unavailable");

        assertCommandFailure(availabilityCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_notStudent_throwsCommandException() {
        Index nonStudentTIndex = Index.fromOneBased(3);
        AvailabilityCommand availabilityCommand = new AvailabilityCommand(nonStudentTIndex, "Unavailable");

        assertCommandFailure(availabilityCommand, model, AvailabilityCommand.MESSAGE_PERSON_NOT_TA);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AvailabilityCommand availabilityCommand = new AvailabilityCommand(outOfBoundIndex, "0/0");

        assertCommandFailure(availabilityCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AvailabilityCommand availabilityFirstCommand = new AvailabilityCommand(INDEX_FIRST_PERSON, "Unavailable");
        AvailabilityCommand attendanceSecondCommand = new AvailabilityCommand(INDEX_SECOND_PERSON, "Unavailable");

        // same object -> returns true
        assertTrue(availabilityFirstCommand.equals(availabilityFirstCommand));

        // same values -> returns true
        AvailabilityCommand availabilityFirstCommandCopy = new AvailabilityCommand(INDEX_FIRST_PERSON, "Unavailable");
        assertTrue(availabilityFirstCommand.equals(availabilityFirstCommandCopy));

        // different types -> returns false
        assertFalse(availabilityFirstCommand.equals(1));

        // null -> returns false
        assertFalse(availabilityFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(availabilityFirstCommand.equals(attendanceSecondCommand));
    }

}
