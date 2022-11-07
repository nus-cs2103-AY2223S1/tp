package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_DATETIME_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.EditTaskCommand.COMMAND_TYPE;
import static seedu.uninurse.logic.commands.EditTaskCommand.MESSAGE_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_ONE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPatients.getTypicalUninurseBook;
import static seedu.uninurse.testutil.TypicalTasks.TYPICAL_TASK_INSULIN;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.NonRecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditTaskCommand}.
 */
class EditTaskCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(null, INDEX_FIRST_ATTRIBUTE, DESC_TASK_INSULIN));
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(INDEX_FIRST_PERSON, null, DESC_TASK_INSULIN));
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_INSULIN);
        assertThrows(NullPointerException.class, () -> editTaskCommand.execute(null));
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        // Use second patient as the first patient in typical patients does not have a task
        Patient patientToEdit = model.getPatient(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));

        Task initialTask = patientToEdit.getTasks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        Task editedTask = new NonRecurringTask(TYPICAL_TASK_INSULIN, DATE_TIME_ONE);

        Patient editedPatient = new PatientBuilder(patientToEdit).withTasks(patientToEdit.getTasks()
                .edit(INDEX_FIRST_ATTRIBUTE.getZeroBased(), editedTask)
                .getInternalList().toArray(Task[]::new)).build();

        EditTaskCommand editTaskCommand =
                new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_TASK_DESCRIPTION_DATETIME_INSULIN);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), initialTask, editedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPatient(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditTaskCommand editTaskCommand =
                new EditTaskCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, DESC_TASK_INSULIN);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use second person in TypicalPatients since there is a task to edit
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Patient patientToEdit = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));

        Task initialTask = patientToEdit.getTasks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        Task editedTask = new NonRecurringTask(TYPICAL_TASK_INSULIN, DATE_TIME_ONE);

        Patient editedPatient = new PatientBuilder(patientToEdit).withTasks(patientToEdit.getTasks()
                .edit(INDEX_FIRST_ATTRIBUTE.getZeroBased(), editedTask)
                .getInternalList().toArray(Task[]::new)).build();

        EditTaskCommand editTaskCommand =
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_TASK_DESCRIPTION_DATETIME_INSULIN);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), initialTask, editedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        expectedModel.setPatient(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        EditTaskCommand editTaskCommand =
                new EditTaskCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, DESC_TASK_INSULIN);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        Patient patient = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));

        Index outOfBoundIndex = Index.fromOneBased(patient.getTasks().size() + 1);
        EditTaskCommand editTaskCommand =
                new EditTaskCommand(INDEX_FIRST_PERSON, outOfBoundIndex, DESC_TASK_INSULIN);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_INDEX);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Patient patient = model.getPatient(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));

        Task initialTask = patient.getTasks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        EditTaskDescriptor duplicateTaskDescriptor = new EditTaskDescriptor(
                Optional.of(initialTask.getTaskDescription()), Optional.of(initialTask.getDateTime()),
                Optional.empty());

        EditTaskCommand editTaskCommand =
                new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, duplicateTaskDescriptor);

        assertCommandFailure(editTaskCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_TASK, patient.getName().toString()));
    }

    @Test
    public void equals() {
        EditTaskCommand editFirstPersonFirstTaskDescriptorStub =
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_TASK_INSULIN);
        EditTaskCommand editSecondPersonFirstTaskDescriptorStub =
                new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_TASK_INSULIN);
        EditTaskCommand editFirstPersonSecondTaskDescriptorStub =
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE, DESC_TASK_INSULIN);
        EditTaskCommand editFirstPersonFirstTaskDescriptorStubOther =
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_TASK_DESCRIPTION_INSULIN);

        // same object -> returns true
        assertEquals(editFirstPersonFirstTaskDescriptorStub, editFirstPersonFirstTaskDescriptorStub);

        // same values -> returns true
        EditTaskCommand editFirstPersonFirstTaskDescriptorStubCopy =
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_TASK_INSULIN);
        assertEquals(editFirstPersonFirstTaskDescriptorStub, editFirstPersonFirstTaskDescriptorStubCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstPersonFirstTaskDescriptorStub);

        // null -> returns false
        assertNotEquals(null, editFirstPersonFirstTaskDescriptorStub);

        // different person index -> returns false
        assertNotEquals(editFirstPersonFirstTaskDescriptorStub, editSecondPersonFirstTaskDescriptorStub);

        // different task index -> returns false
        assertNotEquals(editFirstPersonFirstTaskDescriptorStub, editFirstPersonSecondTaskDescriptorStub);

        // different descriptor -> returns false
        assertNotEquals(editFirstPersonFirstTaskDescriptorStub,
                editFirstPersonFirstTaskDescriptorStubOther);
    }
}
