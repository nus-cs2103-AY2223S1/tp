package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleCodeStartsWithKeywordPredicateTest {
    private static final String MODULE_CODE_STR_CS2106 = CS2106.getModuleCodeAsUpperCaseString();
    private static final String MODULE_CODE_STR_MA2001 = MA2001.getModuleCodeAsUpperCaseString();

    @Test
    public void equals() {
        ModuleCodeStartsWithKeywordPredicate firstPredicate =
                new ModuleCodeStartsWithKeywordPredicate(MODULE_CODE_STR_CS2106);
        ModuleCodeStartsWithKeywordPredicate secondPredicate =
                new ModuleCodeStartsWithKeywordPredicate(MODULE_CODE_STR_MA2001);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleCodeStartsWithKeywordPredicate firstPredicateCopy =
                new ModuleCodeStartsWithKeywordPredicate(MODULE_CODE_STR_CS2106);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different module -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleCodeStartsWithKeyword_returnsTrue() {
        // One keyword
        ModuleCodeStartsWithKeywordPredicate predicate = new ModuleCodeStartsWithKeywordPredicate("MA");
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(MODULE_CODE_STR_MA2001).build()));

        // Mixed-case keywords
        predicate = new ModuleCodeStartsWithKeywordPredicate("mA");
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(MODULE_CODE_STR_MA2001).build()));
    }

    @Test
    public void test_moduleCodeDoesNotStartsWithKeyword_returnsFalse() {
        // Zero keywords
        ModuleCodeStartsWithKeywordPredicate predicate = new ModuleCodeStartsWithKeywordPredicate(" ");
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(MODULE_CODE_STR_CS2106).build()));

        // Non-matching keyword
        predicate = new ModuleCodeStartsWithKeywordPredicate("MA");
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(MODULE_CODE_STR_CS2106).build()));
    }
}
