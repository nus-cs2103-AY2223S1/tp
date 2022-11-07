package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.FINISH_TP;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.TaskList;

public class DeadlineComparatorTest {

    private TaskList firstTaskList;
    private TaskList secondTaskList;
    private DeadlineComparator firstDeadlineComparator;
    private DeadlineComparator secondDeadlineComparator;

    @BeforeEach
    public void setUp() {
        firstTaskList = getTypicalTaskList();
        secondTaskList = new TaskList();
        secondTaskList.addTask(FINISH_TP);
        Task finishTpCopy = new Task(
                new TaskName("Finish TP 1"), FINISH_TP.getModule(), FINISH_TP.getDeadline(), FINISH_TP.getStatus());
        secondTaskList.addTask(finishTpCopy);
        firstDeadlineComparator = new DeadlineComparator(firstTaskList);
        secondDeadlineComparator = new DeadlineComparator(secondTaskList);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(firstDeadlineComparator.equals(firstDeadlineComparator));

        // same values -> returns true
        DeadlineComparator firstDeadlineComparatorCopy = new DeadlineComparator(firstTaskList);
        assertTrue(firstDeadlineComparator.equals(firstDeadlineComparatorCopy));

        // different comparator -> returns false
        assertFalse(firstDeadlineComparator.equals(secondDeadlineComparator));

        // different types -> returns false
        assertFalse(firstDeadlineComparator.equals(1));

        // null -> returns false
        assertFalse(firstDeadlineComparator.equals(null));

    }

    @Test
    public void compare() {
        Comparator<Task> comparator = new DeadlineComparator();
        assertEquals(-1, firstDeadlineComparator.compare(
                firstTaskList.getTaskList().get(0), firstTaskList.getTaskList().get(1)));
        assertEquals(1, firstDeadlineComparator.compare(
                firstTaskList.getTaskList().get(1), firstTaskList.getTaskList().get(0)));
        assertEquals(-1, secondDeadlineComparator.compare(
                secondTaskList.getTaskList().get(1), secondTaskList.getTaskList().get(0)));
        assertEquals(1, secondDeadlineComparator.compare(
                secondTaskList.getTaskList().get(0), secondTaskList.getTaskList().get(1)));
    }
}
