package seedu.address.model.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class ModuleContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleContainsKeywordsPredicate firstPredicateCopy =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleContainsKeywords_returnsTrue() {
        // One keyword
        ModuleContainsKeywordsPredicate predicate =
                new ModuleContainsKeywordsPredicate(Collections.singletonList("CS2013T"));
        assertTrue(predicate.test(new PersonBuilder().withModule("CS2013T").build()));

        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("CS2103T"));
        assertTrue(predicate.test(new PersonBuilder().withModule("CS2103T").build()));


        // Mixed-case keywords
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("Cs2103t"));
        assertTrue(predicate.test(new PersonBuilder().withModule("CS2103T").build()));
    }

    @Test
    public void test_moduleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleContainsKeywordsPredicate predicate = new ModuleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withModule("cs2103t").build()));

        // Non-matching keyword
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("cs2103t"));
        assertFalse(predicate.test(new PersonBuilder().withModule("cs2100").build()));

        // Keywords match name, deadline, tags and isDone, but does not match module
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("tp", "2022-10-07", "highPriority", "false"));
        assertFalse(predicate.test(new PersonBuilder().withName("tp").withModule("cs2103t")
                .withDeadline("2022-10-07").withTags("highPriority").withIsDone(false).build()));
    }
    @Test
    public void isString_validInputs_correctResult() {
        // Zero keyword predicate
        ModuleContainsKeywordsPredicate predicate = new ModuleContainsKeywordsPredicate(Collections.emptyList());
        assertEquals("Modules containing ''", predicate.toString());
        // Regular test case
        predicate = new ModuleContainsKeywordsPredicate(List.of("Cs2103T"));
        assertEquals("Modules containing 'Cs2103T'", predicate.toString());
    }
}
