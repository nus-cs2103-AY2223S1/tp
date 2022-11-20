package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleCodeContainsKeywordPredicateTest {

    @Test
    public void equals() {
        ModuleCode firstPredicateKeyword = new ModuleCode("CS2107");
        ModuleCode secondPredicateKeyword = new ModuleCode("CS2103T");

        ModuleCodeContainsKeywordPredicate firstPredicate =
                new ModuleCodeContainsKeywordPredicate(firstPredicateKeyword);
        ModuleCodeContainsKeywordPredicate secondPredicate =
                new ModuleCodeContainsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleCodeContainsKeywordPredicate firstPredicateCopy =
                new ModuleCodeContainsKeywordPredicate(firstPredicateKeyword);
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
        ModuleCodeContainsKeywordPredicate predicate =
                new ModuleCodeContainsKeywordPredicate(new ModuleCode("CS2106"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2106").build()));

        // Mixed-case keywords
        predicate = new ModuleCodeContainsKeywordPredicate(new ModuleCode("cS2107"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode("CS2107").build()));
    }

    @Test
    public void test_moduleCodeDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        ModuleCodeContainsKeywordPredicate predicate =
                new ModuleCodeContainsKeywordPredicate(new ModuleCode("CS2103T"));
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode("CS3230").build()));

    }
}
