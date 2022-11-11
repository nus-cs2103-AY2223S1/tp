package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;
import seedu.intrack.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddTaskCommand}.
 */
public class AddTaskCommandTest {

    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());

    private Task testTask1 = new Task("testTask1", "11-11-2022 11:00");
    private Task testTask2 = new Task("testTask2", "12-12-2022 12:00");

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_validTaskSelectedList_success() {
        Internship selectedInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        // An internship must be selected before AddTask can be used
        model.updateSelectedInternship(a -> a.isSameInternship(selectedInternship));
        AddTaskCommand addTaskCommand = new AddTaskCommand(testTask1);

        ModelManager expectedModel = new ModelManager(model.getInTrack(), new UserPrefs());

        List<Task> editedTasks = new ArrayList<>(selectedInternship.getTasks());
        editedTasks.add(testTask1);
        editedTasks.sort(Comparator.comparing(task -> task.taskTime));

        Internship expectedInternship = new InternshipBuilder(selectedInternship).withTaskList(editedTasks).build();
        expectedModel.setInternship(selectedInternship, expectedInternship);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS, expectedInternship);

        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnselectedList_throwsCommandException() {
        // An internship is not selected before AddTask is used
        AddTaskCommand addTaskCommand = new AddTaskCommand(testTask1);

        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_NO_INTERNSHIP_SELECTED);
    }

    @Test
    public void equals() {
        AddTaskCommand addTaskFirstCommand = new AddTaskCommand(testTask1);
        AddTaskCommand addTaskSecondCommand = new AddTaskCommand(testTask2);

        // same object -> returns true
        assertTrue(addTaskFirstCommand.equals(addTaskFirstCommand));

        // same values -> returns true
        AddTaskCommand addTaskFirstCommandCopy = new AddTaskCommand(testTask1);
        assertTrue(addTaskFirstCommand.equals(addTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(addTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(addTaskFirstCommand.equals(addTaskSecondCommand));
    }
}
