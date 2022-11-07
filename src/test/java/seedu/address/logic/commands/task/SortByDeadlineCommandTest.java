package seedu.address.logic.commands.task;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTasksSortedByDeadline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortByDeadlineCommand.
 */
public class SortByDeadlineCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        for (Task task : getTypicalTasksSortedByDeadline()) {
            expectedModel.addTask(task);
        }
    }

    @Test
    public void execute_listIsSorted_success() {
        assertCommandSuccess(new SortByDeadlineCommand(), model, SortByDeadlineCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
