package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_ONE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;
import static seedu.uninurse.testutil.TypicalTasks.TASK_CARE_PLAN;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.NonRecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditTaskCommand}.
 */
class EditTaskCommandTest {
    private static final String TASK_STUB = "Some task";

    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(null, INDEX_FIRST_ATTRIBUTE, TASK_CARE_PLAN));
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(INDEX_FIRST_PERSON, null, TASK_CARE_PLAN));
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                TASK_CARE_PLAN);
        assertThrows(NullPointerException.class, () -> editTaskCommand.execute(null));
    }

    @Test
    public void execute_editTask_success() {
        // Use second patient as the first patient in typical persons does not have a task
        Patient secondPatient = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Task initialTask = secondPatient.getTasks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        Task editedTask = new NonRecurringTask(TASK_STUB);

        Patient editedPatient = new PersonBuilder(secondPatient)
                .withTasks(secondPatient.getTasks().edit(INDEX_FIRST_ATTRIBUTE.getZeroBased(), editedTask)
                        .getInternalList().toArray(Task[]::new))
                // to convert the TaskList into an array of string representation of tasks
                .build();

        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, editedTask);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                INDEX_FIRST_ATTRIBUTE.getOneBased(), editedPatient.getName().toString(), initialTask, editedTask);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(secondPatient, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editTaskCommand, model, expectedMessage,
                EditTaskCommand.EDIT_TASK_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE,
                new NonRecurringTask(TASK_STUB));

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE,
                new NonRecurringTask(TASK_STUB));

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        Patient firstPatient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Index outOfBoundIndex = Index.fromOneBased(firstPatient.getTasks().size() + 1);
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_PERSON, outOfBoundIndex,
                new NonRecurringTask(TASK_STUB));

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_INDEX);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Patient secondPatient = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Task initialTask = secondPatient.getTasks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        Task duplicateTask = new NonRecurringTask(initialTask.getTaskDescription(), initialTask.getDateTime());

        EditTaskCommand editTaskCommand =
                new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, duplicateTask);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                new NonRecurringTask(TASK_STUB, DATE_TIME_ONE));

        // same values -> returns true
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                new NonRecurringTask(TASK_STUB, DATE_TIME_ONE));

        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different index -> returns false
        assertNotEquals(standardCommand, new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE,
                new NonRecurringTask(TASK_STUB)));

        // different task -> returns false
        assertNotEquals(standardCommand, new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                new NonRecurringTask("not task stub")));
    }
}
