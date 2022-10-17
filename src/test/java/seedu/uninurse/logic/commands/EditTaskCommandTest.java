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
                new EditTaskCommand(null, INDEX_FIRST_TASK, TASK_CARE_PLAN));
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(INDEX_FIRST_PERSON, null, TASK_CARE_PLAN));
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK, TASK_CARE_PLAN);
        assertThrows(NullPointerException.class, () -> editTaskCommand.execute(null));
    }

    @Test
    public void execute_editTask_success() {
        Task editedTask = new Task(TASK_STUB);

        // Use second patient as the first patient in typical persons does not have a task
        Patient secondPatient = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(secondPatient)
                .withTasks(secondPatient.getTasks().edit(INDEX_FIRST_TASK.getZeroBased(), editedTask)
                        .getTasks().stream().map(Object::toString).toArray(String[]::new)) // to convert the TaskList
                // into an array of string representation of tasks
                .build();

        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_TASK, editedTask);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(secondPatient, editedPatient);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, INDEX_FIRST_TASK, new Task(TASK_STUB));

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, INDEX_FIRST_TASK, new Task(TASK_STUB));

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        Patient firstPatient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Index outOfBoundIndex = Index.fromOneBased(firstPatient.getTasks().size() + 1);
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_PERSON, outOfBoundIndex, new Task(TASK_STUB));

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_INDEX);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK,
                new Task(TASK_STUB));

        // same values -> returns true
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK,
                new Task(TASK_STUB));
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different index -> returns false
        assertNotEquals(standardCommand, new EditTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_TASK,
                new Task(TASK_STUB)));

        // different task -> returns false
        assertNotEquals(standardCommand, new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK,
                new Task("not task stub")));
    }
}
