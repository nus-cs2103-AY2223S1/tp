package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_TASK_DATE_TIME_FIRST;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_TASK_DESC_FIRST;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_STRING;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;
import static seedu.uninurse.testutil.TypicalTasks.TASK_HEALTH_RECORDS;
import static seedu.uninurse.testutil.TypicalTasks.TASK_INSULIN;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.NonRecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code AddTaskCommand}.
 */
public class AddTaskCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

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
        Patient editedPatient = new PersonBuilder(patientToAddTask)
                .withTasks(new NonRecurringTask(VALID_TASK_DESC_FIRST,
                        new DateTime(VALID_TASK_DATE_TIME_FIRST))).build();
        int lastTaskIndex = editedPatient.getTasks().size() - 1;
        Task addedTask = editedPatient.getTasks().get(lastTaskIndex);

        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_PERSON, addedTask);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS,
                editedPatient.getName().toString(), addedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddTask, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addTaskCommand, model, expectedMessage,
                AddTaskCommand.ADD_TASK_COMMAND_TYPE, expectedModel);
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
                .withTasks(new NonRecurringTask(VALID_TASK_DESC_FIRST,
                        new DateTime(VALID_TASK_DATE_TIME_FIRST))).build();
        int lastTaskIndex = editedPatient.getTasks().size() - 1;
        Task addedTask = editedPatient.getTasks().get(lastTaskIndex);

        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_PERSON, addedTask);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS,
                editedPatient.getName().toString(), addedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddTask, editedPatient);
        expectedModel.updateFilteredPersonList(patient -> patient.equals(editedPatient));
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addTaskCommand, model, expectedMessage,
                AddTaskCommand.ADD_TASK_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, TASK_INSULIN);

        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addDuplicateTask_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Task duplicateTask = new NonRecurringTask(VALID_TASK_DESC_FIRST, new DateTime(DATE_TIME_STRING));
        TaskList updatedTaskList = patient.getTasks().add(duplicateTask);
        Patient patientWithTask = new Patient(patient, updatedTaskList);
        model.setPerson(patient, patientWithTask);

        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_PERSON, duplicateTask);
        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void equals() {
        AddTaskCommand addTaskFirstInsulin = new AddTaskCommand(INDEX_FIRST_PERSON, TASK_INSULIN);
        AddTaskCommand addTaskSecondInsulin = new AddTaskCommand(INDEX_SECOND_PERSON, TASK_INSULIN);
        AddTaskCommand addTaskFirstHealthRecords = new AddTaskCommand(INDEX_FIRST_PERSON, TASK_HEALTH_RECORDS);

        // same object -> returns true
        assertEquals(addTaskFirstInsulin, addTaskFirstInsulin);

        // same values -> returns true
        AddTaskCommand addTaskFirstInsulinCopy = new AddTaskCommand(INDEX_FIRST_PERSON, TASK_INSULIN);
        assertEquals(addTaskFirstInsulin, addTaskFirstInsulinCopy);

        // different types -> returns false
        assertNotEquals(1, addTaskFirstInsulin);

        // null -> returns false
        assertNotEquals(null, addTaskFirstInsulin);

        // different patient index -> returns false
        assertNotEquals(addTaskFirstInsulin, addTaskSecondInsulin);

        // different task -> returns false
        assertNotEquals(addTaskFirstInsulin, addTaskFirstHealthRecords);
    }
}
