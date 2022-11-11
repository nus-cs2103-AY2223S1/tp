package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TitleContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        TitleContainsKeywordPredicate firstPredicate = new TitleContainsKeywordPredicate(firstPredicateKeyword);
        TitleContainsKeywordPredicate secondPredicate = new TitleContainsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordPredicate firstPredicateCopy = new TitleContainsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different teammate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // Matching keyword
        TitleContainsKeywordPredicate predicate = new TitleContainsKeywordPredicate("Thiag");
        assertTrue(predicate.test(new TaskBuilder().withTitle("Thiago Alcantara").build()));


        // Mixed-case keywords
        predicate = new TitleContainsKeywordPredicate("tHiAg");
        assertTrue(predicate.test(new TaskBuilder().withTitle("Thiago Alcantara").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TitleContainsKeywordPredicate predicate = new TitleContainsKeywordPredicate("Ali");
        assertFalse(predicate.test(new TaskBuilder().withTitle("Bobby Dazzler").build()));
    }

}
