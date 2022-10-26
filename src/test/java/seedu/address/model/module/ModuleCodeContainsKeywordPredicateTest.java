package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleCodeContainsKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("CS1101S");
        List<String> secondPredicateKeywordList = Arrays.asList("CS1101S", "CS2100");

        ModuleDetailsContainsKeywordsPredicate firstPredicate =
                new ModuleDetailsContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleDetailsContainsKeywordsPredicate secondPredicate =
                new ModuleDetailsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleDetailsContainsKeywordsPredicate firstPredicateCopy =
                new ModuleDetailsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different module -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleCodeContainsKeywords_returnsTrue() {
        // One keyword
        ModuleDetailsContainsKeywordsPredicate predicate =
                new ModuleDetailsContainsKeywordsPredicate(Collections.singletonList("CS2100"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2100").build()));

        // Multiple keywords
        predicate = new ModuleDetailsContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2040S"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2100 CS2040S").build()));

        // Only one matching keyword
        predicate = new ModuleDetailsContainsKeywordsPredicate(Arrays.asList("CS1101S", "MA1521"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2030S MA1521").build()));

        // Mixed-case keywords
        predicate = new ModuleDetailsContainsKeywordsPredicate(Arrays.asList("cs2100", "MA2100"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2100 ma1521").build()));
    }

    @Test
    public void test_moduleCodeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleDetailsContainsKeywordsPredicate predicate =
                new ModuleDetailsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode("CS2100").build()));

        // Non-matching keyword
        predicate = new ModuleDetailsContainsKeywordsPredicate(Arrays.asList("CS3230"));
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode("MA1521 LSM1301").build()));
    }
}
