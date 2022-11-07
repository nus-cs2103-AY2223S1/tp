package seedu.address.model.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameOrTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameOrTagContainsKeywordsPredicate firstPredicate = new NameOrTagContainsKeywordsPredicate(
                firstPredicateKeywordList);
        NameOrTagContainsKeywordsPredicate secondPredicate = new NameOrTagContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameOrTagContainsKeywordsPredicate firstPredicateCopy = new NameOrTagContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void testMatchingNames_nameOrTagContainsKeywords_returnsTrue() {
        // One name
        NameOrTagContainsKeywordsPredicate predicate = new NameOrTagContainsKeywordsPredicate(
                Collections.singletonList("Tutorial"));
        assertTrue(predicate.test(new PersonBuilder().withName("Tutorial 1").build()));

        // Multiple names matching
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Tutorial", "1"));
        assertTrue(predicate.test(new PersonBuilder().withName("Tutorial 1").build()));

        // Only one matching name
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Tutorial", "2"));
        assertTrue(predicate.test(new PersonBuilder().withName("Tutorial 1").build()));

        // Mixed-case name keywords
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("tuTOrial", "AssIgnMent"));
        assertTrue(predicate.test(new PersonBuilder().withName("Tutorial assignment").build()));
    }

    @Test
    public void testMatchingTags_nameOrTagContainsKeywords_returnsTrue() {
        // One keyword tag
        NameOrTagContainsKeywordsPredicate predicate =
                new NameOrTagContainsKeywordsPredicate(Collections.singletonList("Priority"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Priority", "CS").build()));

        // Multiple keywords tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Priority", "CS"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Priority", "CS").build()));

        // Only one matching keyword tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Priority", "IS"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Priority", "CS").build()));

        // Mixed-case keywords tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("priOriTy", "cS"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Priority", "CS").build()));
    }

    @Test
    public void testNames_nameOrTagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords matching name
        NameOrTagContainsKeywordsPredicate predicate = new NameOrTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Tutorial").build()));

        // Non-matching keyword for name
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Lab"));
        assertFalse(predicate.test(new PersonBuilder().withName("Tutorial 1").build()));

        // Keywords match module and deadline but does not match name
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Lab", "CS2100", "2022-10-07"));
        assertFalse(predicate.test(new PersonBuilder().withName("Tutorial").withModule("CS2100")
                .withDeadline("2022-10-07").build()));
    }

    @Test
    public void testTags_nameOrTagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrTagContainsKeywordsPredicate predicate = new NameOrTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("Priority").build()));

        // Non-matching tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Important"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Priority", "High").build()));

        // Keywords match module and deadline but does not match tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Priority", "CS2100", "2022-10-07"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Important").withModule("CS2100")
                .withDeadline("2022-10-07").build()));
    }

    @Test
    public void toString_validInputs_correctResult() {
        // Zero keyword predicate
        NameOrTagContainsKeywordsPredicate predicate = new NameOrTagContainsKeywordsPredicate(Collections.emptyList());
        assertEquals("Names or tags containing ''", predicate.toString());
        // Regular test case
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("taskName", "task2"));
        assertEquals("Names or tags containing [taskName, task2]", predicate.toString());
    }
}
