package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class DescriptionContainsKeywordsPredicateTest {


    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("task"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("task").build()));

        //One keyword mixed case
        predicate = new DescriptionContainsKeywordsPredicate(Collections.singletonList("task"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TAsK").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("task", "one"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TasK ONe").build()));

        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("task", "one"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TasK Two Task ONe").build()));

        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("as"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TasK Two Task ONe").build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder()
                .withTaskDescription("Task one").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("task"));
        assertFalse(predicate.test(new TaskBuilder()
                .withTaskDescription("homework paper").build()));

    }
}
