package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DeadlineComparator;
import seedu.address.model.task.DefaultComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTaskCommand.
 */
public class ListTaskCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFilteredDefaultComparator_showsSameList() {
        assertCommandSuccess(new ListTaskCommand(new DefaultComparator()),
                model, ListTaskCommand.MESSAGE_LIST_DEFAULT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilteredDefaultComparator_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST);
        Assertions.assertTrue(model.getTaskList().equals(expectedModel.getTaskList()));
        assertCommandSuccess(new ListTaskCommand(new DefaultComparator()),
                model, ListTaskCommand.MESSAGE_LIST_DEFAULT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsNotFilteredDeadlineComparator_showsSameList() {
        expectedModel.updateSortedTaskList(new DeadlineComparator());
        assertCommandSuccess(new ListTaskCommand(new DeadlineComparator()),
                model, ListTaskCommand.MESSAGE_LIST_DEADLINE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilteredDeadlineComparator_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST);
        Assertions.assertTrue(model.getTaskList().equals(expectedModel.getTaskList()));
        expectedModel.updateSortedTaskList(new DeadlineComparator());
        assertCommandSuccess(new ListTaskCommand(new DeadlineComparator()),
                model, ListTaskCommand.MESSAGE_LIST_DEADLINE_SUCCESS, expectedModel);
    }

}
