package jeryl.fyp.model.student;

import static jeryl.fyp.testutil.TypicalDeadlines.TASK_A;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_A_COPY;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_B;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_B_DIFF_TIME;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_C;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_E;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void isSameDeadlineName() {
        // same object -> returns true
        assertTrue(TASK_A.isSameDeadlineName(TASK_A));

        // null -> returns false
        assertFalse(TASK_A.isSameDeadlineName(null));

        // different Deadline -> returns false
        assertFalse(TASK_A.isSameDeadlineName(TASK_B));

        /* same name + different time -> returns false */
        assertTrue(TASK_B_DIFF_TIME.isSameDeadlineName(TASK_B));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(TASK_A.equals(TASK_A));
        // diff object but with same Name & Time -> returns true
        assertTrue(TASK_A.equals(TASK_A_COPY));

        // null -> returns false
        assertFalse(TASK_A.equals(null));

        // different type -> returns false
        assertFalse(TASK_A.equals(5));

        // different student -> returns false
        assertFalse(TASK_A.equals(TASK_C));

        // same time + different name -> returns false
        assertFalse(TASK_C.equals(TASK_E));

        // same same + different time -> returns false
        assertFalse(TASK_B.equals(TASK_B_DIFF_TIME));
    }

    @Test
    public void toStringTest() {
        // different variables that contain the same task
        assertTrue(TASK_A.toString().equals(TASK_A_COPY.toString()));
    }
}
