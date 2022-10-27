package tracko.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tracko.testutil.InventoryItemBuilder;

public class InventoryItemContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("keychain");
        List<String> secondPredicateKeywordList = Arrays.asList("golden", "keychain");

        ItemContainsKeywordsPredicate firstPredicate = new ItemContainsKeywordsPredicate(firstPredicateKeywordList);
        ItemContainsKeywordsPredicate secondPredicate = new ItemContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ItemContainsKeywordsPredicate firstPredicateCopy =
                new ItemContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ItemContainsKeywordsPredicate predicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("Chair"));
        assertTrue(predicate.test(new InventoryItemBuilder().withItemName("Wooden Dining Chair").build()));

        // Multiple keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Wooden", "Dining"));
        assertTrue(predicate.test(new InventoryItemBuilder().withItemName("Wooden Dining Chair").build()));

        // Only one matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Chair", "Wooden"));
        assertTrue(predicate.test(new InventoryItemBuilder().withItemName("Wooden Dining Chair").build()));

        // Mixed-case keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("cHaIR", "wOoDeN"));
        assertTrue(predicate.test(new InventoryItemBuilder().withItemName("Wooden Dining Chair").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemContainsKeywordsPredicate predicate = new ItemContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InventoryItemBuilder().withItemName("Wooden Dining Chair").build()));

        // Non-matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Banana"));
        assertFalse(predicate.test(new InventoryItemBuilder().withItemName("Wooden Dining Chair").build()));

        // Keywords match phone, email and address, but does not match order name
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Gold", "Silver", "pLaTeS", "spOoN"));
        assertFalse(predicate.test(new InventoryItemBuilder().withItemName("Wooden Dining Chair").build()));
    }
}
