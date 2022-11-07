package seedu.codeConnect.logic.commands;

import static seedu.codeConnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.codeConnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeConnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeConnect.testutil.TypicalTasks.LAB_3;
import static seedu.codeConnect.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.codeConnect.model.Model;
import seedu.codeConnect.model.ModelManager;
import seedu.codeConnect.model.UserPrefs;
import seedu.codeConnect.model.task.Task;

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
