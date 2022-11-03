package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commons.Criteria;
import seedu.address.testutil.CriteriaBuilder;

/**
 * Integration testing of SortTaskCommand together with some unit testing
 */
public class SortTaskCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortTaskCommand(null));
    }

    @Test
    public void execute_validCriteriaUnFilteredList_success() {
        Criteria criteria = new CriteriaBuilder().build();
        expectedModel.sortTaskList(criteria);
        assertCommandSuccess(new SortTaskCommand(criteria), model,
                SortTaskCommand.TASK_SORTED_SUCCESSFULLY, expectedModel);
    }

    @Test
    public void execute_validCriteriaFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);
        Criteria criteria = new CriteriaBuilder().withCriteria("description").build();
        expectedModel.sortTaskList(criteria);

        //Checks both have same number of filtered items
        assertEquals(expectedModel.getFilteredTaskList().size(), model.getFilteredTaskList().size());
        //The model should sort what is shown
        assertCommandSuccess(new SortTaskCommand(criteria), model,
                SortTaskCommand.TASK_SORTED_SUCCESSFULLY, expectedModel);
    }

    @Test
    public void execute_validCriteriaEmptyList_throwsCommandException() {
        Criteria criteria = new CriteriaBuilder().build();
        model = new ModelManager();
        String expectedMessage = SortTaskCommand.NO_TASK_TO_SORT;
        assertCommandFailure(new SortTaskCommand(criteria), model, SortTaskCommand.NO_TASK_TO_SORT);
    }

    @Test
    public void testEquals() {
        Criteria priorityCriteria = new CriteriaBuilder().build();
        Criteria deadlineCriteria = new CriteriaBuilder().withCriteria("deadline").build();
        SortTaskCommand sortTaskCommandPriority = new SortTaskCommand(priorityCriteria);
        SortTaskCommand sortTaskCommandPriorityCopy = new SortTaskCommand(priorityCriteria);
        SortTaskCommand sortTaskCommandDeadline = new SortTaskCommand(deadlineCriteria);

        //Checks that SortTaskCommand is same as itself
        assertTrue(sortTaskCommandPriority.equals(sortTaskCommandPriority));

        //Checks that SortTaskCommand is equal with another SortTaskCommand with same criteria
        assertTrue(sortTaskCommandPriority.equals(sortTaskCommandPriorityCopy));

        //Checks that SortTaskCommand is not equal to null
        assertFalse(sortTaskCommandPriority.equals(null));

        //Checks that SortTaskCommand is not equal to another SortTaskCommand with different criteria
        assertFalse(sortTaskCommandPriority.equals(sortTaskCommandDeadline));

        //Checks that SortTaskCommand is not equal to another object type
        assertFalse(sortTaskCommandPriority.equals(5));
    }
}
