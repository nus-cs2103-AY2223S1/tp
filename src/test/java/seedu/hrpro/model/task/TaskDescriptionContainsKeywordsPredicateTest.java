package seedu.hrpro.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hrpro.testutil.TaskBuilder;

public class TaskDescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        final List<String> firstPredicateKeyword = Collections.singletonList("satu");
        final List<String> secondPredicateKeyword = Arrays.asList("satu", "dua");

        TaskDescriptionContainsKeywordsPredicate firstPredicate =
                new TaskDescriptionContainsKeywordsPredicate(firstPredicateKeyword);
        TaskDescriptionContainsKeywordsPredicate secondPredicate =
                new TaskDescriptionContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        assertTrue(firstPredicate.equals(new TaskDescriptionContainsKeywordsPredicate(firstPredicateKeyword)));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different types -> returns false
        assertFalse(firstPredicate.equals("Hello"));

        // different task name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeyword_returnsTrue() {
        // without spaces
        TaskDescriptionContainsKeywordsPredicate predicate =
                new TaskDescriptionContainsKeywordsPredicate(Collections.singletonList("Abort"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Abort Now").build()));

        // with spaces
        predicate = new TaskDescriptionContainsKeywordsPredicate(Arrays.asList("Abort Now"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Abort Now").build()));

        // mixed-case keywords
        predicate = new TaskDescriptionContainsKeywordsPredicate(Collections.singletonList("abOrt"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Abort Now").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnFalse() {

        // Zero keywords
        TaskDescriptionContainsKeywordsPredicate predicate =
                new TaskDescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withDescription("Abort Now").build()));

        // No matching keywords
        predicate = new TaskDescriptionContainsKeywordsPredicate(Arrays.asList("Clean"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("Abort Now").build()));

    }
}
