package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTaskCommand.
 */
public class ListTasksCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    class EmptyModelStub extends ModelManager {
        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return FXCollections.emptyObservableList();
        }
    }

    @Test
    public void execute_listTaskIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTasksCommand(), model, ListTasksCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyList() {
        EmptyModelStub emptymodelStub = new EmptyModelStub();
        assertEquals(new ListTasksCommand().execute(emptymodelStub), new CommandResult(ListTasksCommand.EMPTY_LIST));
    }
    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListTasksCommand(), model, ListTasksCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
