package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TaskBuilder.DEFAULT_TASK_DEADLINE;
import static jarvis.testutil.TaskBuilder.DEFAULT_TASK_DESC;
import static jarvis.testutil.TypicalTasks.getTypicalTaskBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Task;
import jarvis.model.UserPrefs;
import jarvis.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddTaskCommand}.
 */
public class AddTaskCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_newTask_success() {
        Task validTask = new TaskBuilder().build();

        Model expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.addTask(validTask);

        assertCommandSuccess(new AddTaskCommand(validTask), model,
                String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getTaskBook().getTaskList().get(0);
        assertCommandFailure(new AddTaskCommand(taskInList), model, AddTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void equals() {
        Task validTask = new TaskBuilder().build();
        Task validTaskDifferentDesc = new TaskBuilder().withDesc(DEFAULT_TASK_DESC + " different desc").build();
        Task validTaskDifferentDeadline = new TaskBuilder().withDeadline(DEFAULT_TASK_DEADLINE.plusDays(1)).build();
        AddTaskCommand addValidTask = new AddTaskCommand(validTask);
        AddTaskCommand addValidTaskDifferentDesc = new AddTaskCommand(validTaskDifferentDesc);
        AddTaskCommand addValidTaskDifferentDeadline = new AddTaskCommand(validTaskDifferentDeadline);

        // same object -> returns true
        assertTrue(addValidTask.equals(addValidTask));

        // same values -> returns true
        AddTaskCommand addValidTaskCopy = new AddTaskCommand(validTask);
        assertTrue(addValidTask.equals(addValidTaskCopy));

        // different types -> returns false
        assertFalse(addValidTask.equals(1));

        // null -> returns false
        assertFalse(addValidTask.equals(null));

        // different description -> returns false
        assertFalse(addValidTask.equals(addValidTaskDifferentDesc));

        // different deadline -> returns false
        assertFalse(addValidTask.equals(addValidTaskDifferentDeadline));
    }
}
