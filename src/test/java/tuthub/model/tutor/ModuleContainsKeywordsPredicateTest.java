package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

public class ModuleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("cs2100");
        List<String> secondPredicateKeywordList = Arrays.asList("cs2100", "cs2105");

        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> return true
        ModuleContainsKeywordsPredicate firstPredicateCopy =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleContainsKeywords_returnsTrue() {
        // One keyword
        ModuleContainsKeywordsPredicate predicate =
                new ModuleContainsKeywordsPredicate(Collections.singletonList("cs2105"));
        assertTrue(predicate.test(new TutorBuilder().withName("Alice").withModule("cs2105").build()));

        // Mixed case keywords
        predicate = new ModuleContainsKeywordsPredicate(Collections.singletonList("cS2105"));
        assertTrue(predicate.test(new TutorBuilder().withName("Alice").withModule("cs2105").build()));

        // Partial keywords
        predicate = new ModuleContainsKeywordsPredicate(Collections.singletonList("105"));
        assertTrue(predicate.test(new TutorBuilder().withModule("cs2105").build()));
    }

    @Test
    public void test_moduleDoesNotContainKeywords_returnFalse() {
        // Zero keywords
        ModuleContainsKeywordsPredicate predicate =
                new ModuleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("cs2100"));
        assertFalse(predicate.test(new TutorBuilder().withName("Alice").withModule("cs2105").build()));

        // Keywords match phone and email, but does not match module
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("99999999", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new TutorBuilder().withName("Alice").withPhone("99999999")
                .withEmail("alice@email.com").withModule("cs2105").build()));
    }
}
