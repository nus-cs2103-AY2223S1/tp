package seedu.address.logic.commands.task;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTaskCommand}.
 */
public class AddTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() {
        Task validTask = new TaskBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskPanel(), new UserPrefs());
        expectedModel.addTask(validTask);

        assertCommandSuccess(
            new AddTaskCommand(validTask),
            model,
            String.format(AddTaskCommand.MESSAGE_SUCCESS,
                    validTask.getTitle(),
                    validTask.getDeadline(),
                    validTask.getProject(),
                    validTask.getAssignedContacts()),
            expectedModel
        );
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getTaskPanel().getTaskList().get(0);
        assertCommandFailure(
            new AddTaskCommand(taskInList),
            model,
            String.format(AddTaskCommand.MESSAGE_DUPLICATE_TASK, taskInList.getTitle())
        );
    }
}
