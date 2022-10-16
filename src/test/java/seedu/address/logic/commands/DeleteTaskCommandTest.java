package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteTaskCommand}.
 */
class DeleteTaskCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(INDEX_SECOND_PERSON, null));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, INDEX_FIRST_TASK));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_TASK);
        assertThrows(NullPointerException.class, () -> deleteTaskCommand.execute(null));
    }

    @Test
    void execute_validIndicesUnfilteredList_success() {
        // use third person in TypicalPersons since there is one task to delete
        Patient patientToDeleteTask = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToDeleteTask).withTasks().build();
        Task deletedTask = patientToDeleteTask.getTasks().get(INDEX_FIRST_TASK.getZeroBased());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_THIRD_PERSON, INDEX_FIRST_TASK);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                editedPatient.getName().toString(), deletedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteTask, editedPatient);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundPatientIndex, INDEX_FIRST_TASK);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use third person in TypicalPersons since there is one task to delete
        showPersonAtIndex(model, INDEX_THIRD_PERSON);

        Patient patientToDeleteTask = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTasks().build();
        Task deletedTask = patientToDeleteTask.getTasks().get(INDEX_FIRST_TASK.getZeroBased());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                editedPatient.getName().toString(), deletedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteTask, editedPatient);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex, INDEX_FIRST_TASK);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_invalidTaskIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundTaskIndex = Index.fromOneBased(patient.getTasks().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, outOfBoundTaskIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_INDEX);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteTaskFirstCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK);
        DeleteTaskCommand deleteTaskSecondCommand = new DeleteTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_TASK);
        DeleteTaskCommand deleteTaskThirdCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_TASK);

        // same object -> returns true
        assertEquals(deleteTaskFirstCommand, deleteTaskFirstCommand);

        // same values -> returns true
        DeleteTaskCommand deleteTaskFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK);
        assertEquals(deleteTaskFirstCommand, deleteTaskFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteTaskFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteTaskFirstCommand);

        // different person index -> returns false
        assertNotEquals(deleteTaskFirstCommand, deleteTaskSecondCommand);

        // different task index -> returns false
        assertNotEquals(deleteTaskFirstCommand, deleteTaskThirdCommand);
    }
}
