package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleCodeMatchesKeywordsPredicateTest {
    private static final String MODULECODESTR_CS2106 = CS2106.getModuleCodeAsUpperCaseString();
    private static final String MODULECODESTR_MA2001 = MA2001.getModuleCodeAsUpperCaseString();

    @Test
    public void equals() {
        ModuleCodeMatchesKeywordPredicate firstPredicate = new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_CS2106);
        ModuleCodeMatchesKeywordPredicate secondPredicate = new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_MA2001);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleCodeMatchesKeywordPredicate firstPredicateCopy =
                new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_CS2106);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different module -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleCodeMatchesKeywords_returnsTrue() {
        // One keyword
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_CS2106);
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(MODULECODESTR_CS2106).build()));

        // Mixed-case keywords
        predicate = new ModuleCodeMatchesKeywordPredicate("cS2106");
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(MODULECODESTR_CS2106).build()));
    }

    @Test
    public void test_moduleCodeDoesNotMatchesKeywords_returnsFalse() {
        // Zero keywords
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(" ");
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(MODULECODESTR_CS2106).build()));

        // Non-matching keyword
        predicate = new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_MA2001);
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(MODULECODESTR_CS2106).build()));
    }
}
