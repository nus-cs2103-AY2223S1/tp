package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;

public class TaskContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskContainsKeywordsPredicate firstPredicate = new TaskContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskContainsKeywordsPredicate secondPredicate = new TaskContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsKeywordsPredicate firstPredicateCopy = new TaskContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TaskContainsKeywordsPredicate predicate = new
                TaskContainsKeywordsPredicate(Collections.singletonList("Science"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Multiple keywords
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Science", "Math"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Only one matching keyword
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Math", "English"));
        assertTrue(predicate.test(new Task(new TaskName("Science English homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Mixed-case keywords
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("sCIeNce", "maTH"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new Task(new TaskName("Science homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Non-matching keyword
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("English"));
        assertFalse(predicate.test(new Task(new TaskName("Science homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));
    }
}
