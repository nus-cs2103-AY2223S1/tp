package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.testutil.ModuleBuilder;

public class ModuleCodeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModuleCodeContainsKeywordsPredicate firstPredicate =
                new ModuleCodeContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleCodeContainsKeywordsPredicate secondPredicate =
                new ModuleCodeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleCodeContainsKeywordsPredicate firstPredicateCopy =
                new ModuleCodeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleCodeContainsKeywords_returnsTrue() {
        // One keyword
        ModuleCodeContainsKeywordsPredicate predicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("CS2100"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2100 CS2101").build()));

        // Multiple keywords
        predicate = new ModuleCodeContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2101"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2100 CS2101").build()));

        // Only one matching keyword
        predicate = new ModuleCodeContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2109s"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2103T CS2100").build()));

        // Mixed-case keywords
        predicate = new ModuleCodeContainsKeywordsPredicate(Arrays.asList("cs2100", "cS2109S"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2100 CS2109S").build()));
    }

    @Test
    public void test_moduleCodeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleCodeContainsKeywordsPredicate predicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode("CS2100").build()));

        // Non-matching keyword
        predicate = new ModuleCodeContainsKeywordsPredicate(Arrays.asList("CS1101"));
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode("CS2109 CS2100").build()));
    }
}
