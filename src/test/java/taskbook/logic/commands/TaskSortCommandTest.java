package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskSortAddedChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.logic.commands.tasks.TaskSortDescriptionAlphabeticalCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.testutil.TypicalTaskBook;

public class TaskSortCommandTest {
    @Test
    public void equals() {
        TaskSortAddedChronologicalCommand taskSortFirstCommand = new TaskSortAddedChronologicalCommand();
        TaskSortDescriptionAlphabeticalCommand taskSortSecondCommand = new TaskSortDescriptionAlphabeticalCommand();
        // same object -> returns true
        assertTrue(taskSortFirstCommand.equals(taskSortFirstCommand));

        // same command type -> returns true
        TaskSortAddedChronologicalCommand taskSortFirstCommandCopy = new TaskSortAddedChronologicalCommand();
        assertTrue(taskSortFirstCommand.equals(taskSortFirstCommandCopy));

        // different object types -> returns false
        assertFalse(taskSortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(taskSortFirstCommand.equals(null));

        // different sort type -> returns false
        assertFalse(taskSortFirstCommand.equals(taskSortSecondCommand));
    }

    @Test
    public void execute_taskSortAddedChronological_commandSuccess() {
        TaskSortAddedChronologicalCommand command = new TaskSortAddedChronologicalCommand();
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        model.updateSortedTaskList((t1, t2) -> -1);
        String expectedMessage = String.format(TaskSortCommand.MESSAGE_SORT_TASK_SUCCESS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_taskSortDescriptionAlphabetical_commandSuccess() {
        TaskSortDescriptionAlphabeticalCommand command = new TaskSortDescriptionAlphabeticalCommand();
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.updateSortedTaskList((t1, t2) -> t1.compareByDescriptionAlphabeticalTo(t2));
        String expectedMessage = String.format(TaskSortCommand.MESSAGE_SORT_TASK_SUCCESS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
