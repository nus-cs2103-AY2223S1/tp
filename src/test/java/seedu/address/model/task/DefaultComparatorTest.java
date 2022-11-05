package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.FINISH_TP;
import static seedu.address.testutil.TypicalTasks.LAB_2;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.TaskList;

public class DefaultComparatorTest {

    private TaskList firstTaskList;
    private TaskList secondTaskList;
    private DefaultComparator firstDefaultComparator;
    private DefaultComparator secondDefaultComparator;

    @BeforeEach
    public void setUp() {
        firstTaskList = getTypicalTaskList();
        secondTaskList = new TaskList();
        secondTaskList.addTask(FINISH_TP);
        secondTaskList.addTask(LAB_2);
        firstDefaultComparator = new DefaultComparator(firstTaskList);
        secondDefaultComparator = new DefaultComparator(secondTaskList);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(firstDefaultComparator.equals(firstDefaultComparator));

        // same values -> returns true
        DefaultComparator firstDefaultComparatorCopy = new DefaultComparator(firstTaskList);
        assertTrue(firstDefaultComparator.equals(firstDefaultComparatorCopy));

        // different comparator -> returns false
        assertFalse(firstDefaultComparator.equals(secondDefaultComparator));

        // different types -> returns false
        assertFalse(firstDefaultComparator.equals(1));

        // null -> returns false
        assertFalse(firstDefaultComparator.equals(null));

    }

    @Test
    public void compare() {
        // first task before second task in tasklist -> returns 1
        assertEquals(1,
                firstDefaultComparator.compare(firstTaskList.getTaskList().get(0), firstTaskList.getTaskList().get(1)));

        // first task after second task in tasklist -> returns -1
        assertEquals(-1,
                firstDefaultComparator.compare(firstTaskList.getTaskList().get(1), firstTaskList.getTaskList().get(0)));

        // first task and second task has same index in tasklist -> returns 0
        assertEquals(0,
                firstDefaultComparator.compare(firstTaskList.getTaskList().get(0), firstTaskList.getTaskList().get(0)));

        assertEquals(0,
                firstDefaultComparator.compare(firstTaskList.getTaskList().get(1), firstTaskList.getTaskList().get(1)));

    }

}
