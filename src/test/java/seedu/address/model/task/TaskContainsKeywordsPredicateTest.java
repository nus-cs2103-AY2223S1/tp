package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

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
                new Deadline("2022-05-05 15:04"))));

        // Multiple keywords
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Science", "Math"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"))));

        // Only one matching keyword
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Math", "English"));
        assertTrue(predicate.test(new Task(new TaskName("Science English homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"))));

        // Mixed-case keywords
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("sCIeNce", "maTH"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"))));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new Task(new TaskName("Science homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"))));

        // Non-matching keyword
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("English"));
        assertFalse(predicate.test(new Task(new TaskName("Science homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"))));
    }
}
