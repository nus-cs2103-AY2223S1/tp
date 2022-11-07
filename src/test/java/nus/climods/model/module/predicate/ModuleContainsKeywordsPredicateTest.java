package nus.climods.model.module.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import nus.climods.model.module.Module;
import nus.climods.model.module.ModuleStub;

class ModuleContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<Pattern> firstSearchRegexList =
            Collections.singletonList(Pattern.compile("software", Pattern.CASE_INSENSITIVE));
        List<Pattern> secondSearchRegexList =
            Collections.singletonList(Pattern.compile("engineering", Pattern.CASE_INSENSITIVE));

        ModuleContainsKeywordsPredicate firstPredicate = new ModuleContainsKeywordsPredicate(firstSearchRegexList);
        ModuleContainsKeywordsPredicate secondPredicate = new ModuleContainsKeywordsPredicate(secondSearchRegexList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ModuleContainsKeywordsPredicate firstPredicateCopy = new ModuleContainsKeywordsPredicate(firstSearchRegexList);
        assertEquals(firstPredicate, firstPredicateCopy);

        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_moduleContainsKeywords_returnsTrue() {
        Module testModule = new ModuleStub("Software Engineering", "CS2103");
        // Single keyword
        ModuleContainsKeywordsPredicate predicate = new ModuleContainsKeywordsPredicate(
            Collections.singletonList(Pattern.compile("software", Pattern.CASE_INSENSITIVE)));

        assertTrue(predicate.test(testModule));

        // Multiple keywords
        predicate = new ModuleContainsKeywordsPredicate(
            Arrays.asList(Pattern.compile("software", Pattern.CASE_INSENSITIVE),
                Pattern.compile("engineering", Pattern.CASE_INSENSITIVE)));

        assertTrue(predicate.test(testModule));

        // Only one matching keyword
        predicate = new ModuleContainsKeywordsPredicate(
            Arrays.asList(Pattern.compile("software", Pattern.CASE_INSENSITIVE),
                Pattern.compile("testing", Pattern.CASE_INSENSITIVE)));
        assertTrue(predicate.test(testModule));
    }

    @Test
    public void test_moduleDoesNotContainKeywords_returnsFalse() {
        Module testModule = new ModuleStub("Software Engineering", "CS2103");

        // Zero keywords
        ModuleContainsKeywordsPredicate predicate = new ModuleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(testModule));

        // Non-matching keyword
        predicate = new ModuleContainsKeywordsPredicate(
            Collections.singletonList(Pattern.compile("hardware", Pattern.CASE_INSENSITIVE)));
        assertFalse(predicate.test(testModule));
    }
}
