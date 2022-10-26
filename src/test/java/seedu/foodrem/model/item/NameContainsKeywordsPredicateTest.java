package seedu.foodrem.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodrem.testutil.ItemBuilder;

public class NameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different items -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections
                .singletonList("Potato"));
        assertTrue(predicate.test(new ItemBuilder().withItemName("Potato Cucumber").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Potato", "Cucumber"));
        assertTrue(predicate.test(new ItemBuilder().withItemName("Potato Cucumber").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Potato", "Cuc"));
        assertTrue(predicate.test(new ItemBuilder().withItemName("Potato Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("PoTAto", "CuCUmber"));
        assertTrue(predicate.test(new ItemBuilder().withItemName("Potato Cucumber").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ItemBuilder().withItemName("Potato").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(List.of("Carrots"));
        assertFalse(predicate.test(new ItemBuilder().withItemName("Potato Cucumber").build()));

        // Keywords match quantity, bought date and expiry date, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Potato", "12345", "11-11-2022", "12-12-2022"));
        assertFalse(predicate.test(new ItemBuilder()
                .withItemName("Potatoes")
                .withItemQuantity("12345")
                .withItemBoughtDate("11-11-2022")
                .withItemExpiryDate("12-12-2022").build()));
    }
}
