package seedu.codeConnect.logic.commands;

import static seedu.codeConnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeConnect.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.codeConnect.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.codeConnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeConnect.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.codeConnect.model.Model;
import seedu.codeConnect.model.ModelManager;
import seedu.codeConnect.model.UserPrefs;
import seedu.codeConnect.model.task.DeadlineComparator;
import seedu.codeConnect.model.task.DefaultComparator;

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
