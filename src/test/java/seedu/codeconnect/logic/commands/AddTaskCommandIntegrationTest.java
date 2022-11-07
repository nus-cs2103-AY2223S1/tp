package seedu.codeconnect.logic.commands;

import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeconnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeconnect.testutil.TypicalTasks.LAB_3;
import static seedu.codeconnect.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.ModelManager;
import seedu.codeconnect.model.UserPrefs;
import seedu.codeconnect.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTaskCommand}.
 */
public class AddTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() {
        Task validTask = LAB_3;

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        expectedModel.addTask(validTask);

        assertCommandSuccess(new AddTaskCommand(validTask), model,
                String.format(AddTaskCommand.TEMPLATE_SUCCESS, validTask), expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getTaskList().getTaskList().get(0);
        assertCommandFailure(new AddTaskCommand(taskInList), model, AddTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

}
