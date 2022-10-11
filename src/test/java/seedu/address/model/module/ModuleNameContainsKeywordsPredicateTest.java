package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModuleNameContainsKeywordsPredicate firstPredicate =
                new ModuleNameContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleNameContainsKeywordsPredicate secondPredicate =
                new ModuleNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleNameContainsKeywordsPredicate firstPredicateCopy =
                new ModuleNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleNameContainsKeywords_returnsTrue() {
        // One keyword
        ModuleNameContainsKeywordsPredicate predicate =
                new ModuleNameContainsKeywordsPredicate(Collections.singletonList("Security"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Security introduction").build()));

        // Multiple keywords
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("Intro", "Engineering"));
        assertTrue(predicate.test(new ModuleBuilder()
                .withName("Intro to Software Engineering").build()));

        // Only one matching keyword
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("Basic", "Advanced"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Basic CS module").build()));

        // Mixed-case keywords
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("cS", "sWE"));
        assertTrue(predicate.test(new ModuleBuilder().withName("CS SWE").build()));
    }

    @Test
    public void test_moduleNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleNameContainsKeywordsPredicate predicate =
                new ModuleNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ModuleBuilder().withName("Cybersec").build()));

        // Non-matching keyword
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("Cybersecurity"));
        assertFalse(predicate.test(new ModuleBuilder().withName("Networks").build()));

        // Keywords match code, description, but does not match name
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("cs2105",
                "Networks", "apples"));
        assertFalse(predicate.test(new ModuleBuilder().withName("Intro").withModuleCode("cs2105")
                .withModuleDescription("Networks").build()));
    }
}
