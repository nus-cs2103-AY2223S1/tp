package taskbook.logic.commands;

import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static taskbook.logic.commands.CommandTestUtil.showTaskAtIndex;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static taskbook.testutil.TypicalTaskBook.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskListCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TaskListCommand.
 */
public class TaskListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new TaskListCommand(), model, TaskListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new TaskListCommand(), model, TaskListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
