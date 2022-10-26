package seedu.address.model.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("urgent"));
        assertTrue(predicate.test(new PersonBuilder().withTags("urgent").build()));

        predicate = new TagContainsKeywordsPredicate(Arrays.asList("urgent"));
        assertTrue(predicate.test(new PersonBuilder().withTags("urgent").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("URGENT"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Urgent").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("urgent").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("urgent"));
        assertFalse(predicate.test(new PersonBuilder().withTags("anytime").build()));

        // Keywords match name, deadline, module and isDone, but does not match tag
        predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("tp", "2022-10-07", "CS2103T", "highPriority", "false"));
        assertFalse(predicate.test(new PersonBuilder().withName("tp").withModule("CS2103T")
                .withDeadline("2022-10-07").withTags("lowPriority").withIsDone(false).build()));
    }

    @Test
    public void toString_validInputs_correctResult() {
        // Zero keyword predicate
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertEquals("Tag(s) containing ''", predicate.toString());
        // Regular test case
        predicate = new TagContainsKeywordsPredicate(List.of("highPriority"));
        assertEquals("Tag(s) containing 'highPriority'", predicate.toString());
    }
}
