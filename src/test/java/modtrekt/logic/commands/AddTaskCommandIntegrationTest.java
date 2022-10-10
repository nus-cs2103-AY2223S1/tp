package modtrekt.logic.commands;

import static modtrekt.logic.commands.CommandTestUtil.assertCommandSuccess;
import static modtrekt.testutil.TypicalModules.getTypicalModuleList;
import static modtrekt.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modtrekt.model.Model;
import modtrekt.model.ModelManager;
import modtrekt.model.UserPrefs;
import modtrekt.model.task.Task;
import modtrekt.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTaskCommand}.
 */
public class AddTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModuleList(), getTypicalTaskBook(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() {
        Task validTask = new TaskBuilder().build();

        Model expectedModel = new ModelManager(model.getModuleList(), model.getTaskBook(), new UserPrefs());
        System.out.println((validTask));
        System.out.println(expectedModel);
        expectedModel.addTask(validTask);

        assertCommandSuccess(new AddTaskCommand(validTask), model,
                String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), expectedModel);
    }


}
