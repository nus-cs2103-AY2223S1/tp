package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, INDEX_FIRST_ATTRIBUTE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertThrows(NullPointerException.class, () -> deleteTaskCommand.execute(null));
    }

    @Test
    void execute_validIndicesUnfilteredList_success() {
        // use third person in TypicalPersons since there is one task to delete
        Patient patientToDeleteTask = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToDeleteTask).withTasks().build();
        Task deletedTask = patientToDeleteTask.getTasks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_THIRD_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS,
                INDEX_FIRST_ATTRIBUTE.getOneBased(), editedPatient.getName().toString(), deletedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteTask, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage,
                DeleteTaskCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundPatientIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use third person in TypicalPersons since there is one task to delete
        showPersonAtIndex(model, INDEX_THIRD_PERSON);

        Patient patientToDeleteTask = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTasks().build();
        Task deletedTask = patientToDeleteTask.getTasks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS,
                INDEX_FIRST_ATTRIBUTE.getOneBased(), editedPatient.getName().toString(), deletedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteTask, editedPatient);
        expectedModel.updateFilteredPersonList(patient -> patient.equals(editedPatient));
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage,
                DeleteTaskCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE);

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
        DeleteTaskCommand deleteFirstPersonFirstTask = new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteTaskCommand deleteSecondPersonFirstTask =
                new DeleteTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteTaskCommand deleteFirstPersonSecondTask =
                new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE);

        // same object -> returns true
        assertEquals(deleteFirstPersonFirstTask, deleteFirstPersonFirstTask);

        // same values -> returns true
        DeleteTaskCommand deleteFirstPersonFirstTaskCopy =
                new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertEquals(deleteFirstPersonFirstTask, deleteFirstPersonFirstTaskCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstPersonFirstTask);

        // null -> returns false
        assertNotEquals(null, deleteFirstPersonFirstTask);

        // different person index -> returns false
        assertNotEquals(deleteFirstPersonFirstTask, deleteSecondPersonFirstTask);

        // different task index -> returns false
        assertNotEquals(deleteFirstPersonFirstTask, deleteFirstPersonSecondTask);
    }
}
