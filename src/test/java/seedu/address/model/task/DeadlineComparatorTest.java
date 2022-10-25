package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalTasks.FINISH_TP;
import static seedu.address.testutil.TypicalTasks.LAB_2;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;

public class DeadlineComparatorTest {

    @Test
    public void compare() {
        Comparator<Task> comparator = new DeadlineComparator();
        assertEquals(1, comparator.compare(FINISH_TP, LAB_2));
        assertEquals(-1, comparator.compare(LAB_2, FINISH_TP));
        assertEquals(0, comparator.compare(LAB_2, LAB_2));
        assertEquals(0, comparator.compare(LAB_2, new Task(new TaskName("Tutorial 2"), new Module("MA2001"),
                LAB_2.getDeadline(), new Status(true))));
    }
}
