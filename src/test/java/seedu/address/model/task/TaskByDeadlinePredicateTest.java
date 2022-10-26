package seedu.address.model.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TaskByDeadlinePredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskByDeadlinePredicate firstPredicate =
                new TaskByDeadlinePredicate(firstPredicateKeywordList);
        TaskByDeadlinePredicate secondPredicate =
                new TaskByDeadlinePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskByDeadlinePredicate firstPredicateCopy =
                new TaskByDeadlinePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_deadlineOnGivenKeywordDate_returnsTrue() {
        // One keyword
        TaskByDeadlinePredicate predicate =
                new TaskByDeadlinePredicate(Collections.singletonList("2022-10-10"));
        assertTrue(predicate.test(new PersonBuilder().withDeadline("2022-10-10").build()));

        predicate = new TaskByDeadlinePredicate(Arrays.asList("2022-10-10"));
        assertTrue(predicate.test(new PersonBuilder().withDeadline("2022-10-10").build()));
    }

    @Test
    public void test_deadlineAfterGivenKeywordDate_returnsTrue() {
        // One keyword
        TaskByDeadlinePredicate predicate =
                new TaskByDeadlinePredicate(Collections.singletonList("2022-10-10"));
        assertTrue(predicate.test(new PersonBuilder().withDeadline("2022-10-15").build()));

        predicate = new TaskByDeadlinePredicate(Arrays.asList("2022-10-10"));
        assertTrue(predicate.test(new PersonBuilder().withDeadline("2022-10-15").build()));
    }

    @Test
    public void test_deadlineBeforeGivenKeywordDate_returnsFalse() {
        // Zero keywords
        TaskByDeadlinePredicate predicate = new TaskByDeadlinePredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDeadline("2022-10-10").build()));

        // Non-matching keyword
        predicate = new TaskByDeadlinePredicate(Arrays.asList("2022-10-10"));
        assertFalse(predicate.test(new PersonBuilder().withDeadline("2022-10-09").build()));

        // Keywords match name, tag, module and isDone, but deadline is before given date
        predicate = new TaskByDeadlinePredicate(Arrays.asList("tp", "2022-10-07", "CS2103T", "highPriority", "false"));
        assertFalse(predicate.test(new PersonBuilder().withName("tp").withModule("CS2103T")
                .withDeadline("2022-10-05").withTags("highPriority").withIsDone(false).build()));
    }

    @Test
    public void toString_validInputs_correctResult() {
        // Zero keyword predicate
        TaskByDeadlinePredicate predicate = new TaskByDeadlinePredicate(Collections.emptyList());
        assertEquals("Tasks due on ''", predicate.toString());
        // Regular test case
        predicate = new TaskByDeadlinePredicate(List.of("2022-10-07"));
        assertEquals("Tasks due on '2022-10-07'", predicate.toString());
    }
}
