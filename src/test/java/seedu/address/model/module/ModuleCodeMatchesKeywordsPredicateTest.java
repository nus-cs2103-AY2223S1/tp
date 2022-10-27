package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleCodeMatchesKeywordsPredicateTest {
    public static final String VALID_CS2106_MODULE_CODE = "CS2106";
    public static final String VALID_MA2001_MODULE_CODE = "MA2001";

    @Test
    public void equals() {
        ModuleCodeMatchesKeywordPredicate firstPredicate =
                new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE);
        ModuleCodeMatchesKeywordPredicate secondPredicate =
                new ModuleCodeMatchesKeywordPredicate(VALID_MA2001_MODULE_CODE);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same keyword -> returns true
        ModuleCodeMatchesKeywordPredicate firstPredicateCopy =
                new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleCodeMatchesKeywords_returnsTrue() {
        // One keyword
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE);
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).build()));

        // Mixed-case keyword
        predicate = new ModuleCodeMatchesKeywordPredicate("cS2106");
        assertTrue(predicate.test(new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).build()));
    }

    @Test
    public void test_moduleCodeDoesNotMatchKeywords_returnsFalse() {
        // Zero keywords
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(" ");
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).build()));

        // Non-matching keyword
        predicate = new ModuleCodeMatchesKeywordPredicate(VALID_MA2001_MODULE_CODE);
        assertFalse(predicate.test(new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).build()));
    }
}
