package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.TASK_HEALTH_RECORDS;
import static seedu.address.testutil.TypicalTasks.TASK_INSULIN;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code AddTaskCommand}.
 */
public class AddTaskCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, TASK_INSULIN));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_PERSON, TASK_INSULIN);
        assertThrows(NullPointerException.class, () -> addTaskCommand.execute(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToAddTask = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddTask).withTasks(VALID_TASK_DESC_FIRST).build();
        Task addedTask = new Task(VALID_TASK_DESC_FIRST);

        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_PERSON, addedTask);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS,
                editedPatient.getName().toString(), addedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddTask, editedPatient);

        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, TASK_INSULIN);

        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientToAddTask = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTasks(VALID_TASK_DESC_FIRST).build();
        Task addedTask = new Task(VALID_TASK_DESC_FIRST);

        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_PERSON, addedTask);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS,
                editedPatient.getName().toString(), addedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddTask, editedPatient);

        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, TASK_INSULIN);

        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddTaskCommand addTaskFirstCommand = new AddTaskCommand(INDEX_FIRST_PERSON, TASK_INSULIN);
        AddTaskCommand addTaskSecondCommand = new AddTaskCommand(INDEX_SECOND_PERSON, TASK_INSULIN);
        AddTaskCommand addTaskThirdCommand = new AddTaskCommand(INDEX_FIRST_PERSON, TASK_HEALTH_RECORDS);

        // same object -> returns true
        assertEquals(addTaskFirstCommand, addTaskFirstCommand);

        // same values -> returns true
        AddTaskCommand addTaskFirstCommandCopy = new AddTaskCommand(INDEX_FIRST_PERSON, TASK_INSULIN);
        assertEquals(addTaskFirstCommand, addTaskFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addTaskFirstCommand);

        // null -> returns false
        assertNotEquals(null, addTaskFirstCommand);

        // different person --> returns false
        assertNotEquals(addTaskFirstCommand, addTaskSecondCommand);

        // different task -> returns false
        assertNotEquals(addTaskFirstCommand, addTaskThirdCommand);
    }
}
