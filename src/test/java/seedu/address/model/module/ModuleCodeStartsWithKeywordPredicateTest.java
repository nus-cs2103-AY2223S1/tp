package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleCodeStartsWithKeywordPredicateTest {

    @Test
    public void equals() {
        ModuleCodeStartsWithKeywordPredicate firstPredicate =
                new ModuleCodeStartsWithKeywordPredicate(VALID_CS2106_MODULE_CODE);
        ModuleCodeStartsWithKeywordPredicate secondPredicate =
                new ModuleCodeStartsWithKeywordPredicate(VALID_MA2001_MODULE_CODE);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same keyword -> returns true
        ModuleCodeStartsWithKeywordPredicate firstPredicateCopy =
                new ModuleCodeStartsWithKeywordPredicate(VALID_CS2106_MODULE_CODE);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleCodeStartsWithKeyword_returnsTrue() {
        // One keyword
        ModuleCodeStartsWithKeywordPredicate predicate = new ModuleCodeStartsWithKeywordPredicate("MA");
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(VALID_MA2001_MODULE_CODE).build()));

        // Mixed-case keyword
        predicate = new ModuleCodeStartsWithKeywordPredicate("mA");
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(VALID_MA2001_MODULE_CODE).build()));
    }

    @Test
    public void test_moduleCodeDoesNotStartsWithKeyword_returnsFalse() {
        // Zero keywords
        ModuleCodeStartsWithKeywordPredicate predicate = new ModuleCodeStartsWithKeywordPredicate(" ");
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).build()));

        // Non-matching keyword
        predicate = new ModuleCodeStartsWithKeywordPredicate("MA");
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).build()));
    }
}
