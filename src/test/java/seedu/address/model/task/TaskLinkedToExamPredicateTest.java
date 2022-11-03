package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalExams.FINAL_EXAM;
import static seedu.address.testutil.TypicalExams.MIDTERM_EXAM;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_D;

import org.junit.jupiter.api.Test;

public class TaskLinkedToExamPredicateTest {
    @Test
    public void test_taskLinkedToCorrectExam_returnsTrue() {
        TaskLinkedToExamPredicate predicate = new TaskLinkedToExamPredicate(MIDTERM_EXAM);
        assertTrue(predicate.test(TASK_D));
    }

    @Test
    public void test_taskLinkedToWrongExam_returnsFalse() {
        TaskLinkedToExamPredicate predicate = new TaskLinkedToExamPredicate(FINAL_EXAM);
        assertFalse(predicate.test(TASK_D));
    }

    @Test
    public void test_taskUnlinked_returnsFalse() {
        TaskLinkedToExamPredicate predicate = new TaskLinkedToExamPredicate(MIDTERM_EXAM);
        assertFalse(predicate.test(TASK_A));
    }

    @Test
    public void equals() {
        TaskLinkedToExamPredicate firstTaskLinkedToExamPredicate = new TaskLinkedToExamPredicate(MIDTERM_EXAM);
        TaskLinkedToExamPredicate secondTaskLinkedToExamPredicate = new TaskLinkedToExamPredicate(FINAL_EXAM);
        TaskLinkedToExamPredicate firstTaskLinkedToExamPredicateCopy = new TaskLinkedToExamPredicate(MIDTERM_EXAM);

        // same object -> returns true
        assertTrue(firstTaskLinkedToExamPredicate.equals(firstTaskLinkedToExamPredicate));

        // same values -> returns true
        assertTrue(firstTaskLinkedToExamPredicate.equals(firstTaskLinkedToExamPredicateCopy));

        // different types -> returns false
        assertFalse(firstTaskLinkedToExamPredicate.equals(1));

        // null -> returns false
        assertFalse(firstTaskLinkedToExamPredicate.equals(null));

        // different exam
        assertFalse(firstTaskLinkedToExamPredicate.equals(secondTaskLinkedToExamPredicate));
    }
}
