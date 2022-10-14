package tracko.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tracko.model.order.ItemQuantityPair;
import tracko.model.order.OrderContainsKeywordsPredicate;
import tracko.testutil.OrderBuilder;

public class OrderContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OrderContainsKeywordsPredicate firstPredicate = new OrderContainsKeywordsPredicate(firstPredicateKeywordList);
        OrderContainsKeywordsPredicate secondPredicate = new OrderContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderContainsKeywordsPredicate firstPredicateCopy =
                new OrderContainsKeywordsPredicate(firstPredicateKeywordList);
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
        OrderContainsKeywordsPredicate predicate =
                new OrderContainsKeywordsPredicate(Collections.singletonList("Keychain"));
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair("Apple Keychain", 1)).build()));

        // Multiple keywords
        predicate = new OrderContainsKeywordsPredicate(Arrays.asList("Keychain", "Apple"));
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair("Apple Keychain", 1)).build()));

        // Only one matching keyword
        predicate = new OrderContainsKeywordsPredicate(Arrays.asList("Keychain", "Apple"));
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair("Banana Keychain", 1)).build()));

        // Mixed-case keywords
        predicate = new OrderContainsKeywordsPredicate(Arrays.asList("kEyChAiN", "aPpLe"));
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair("Apple Keychain", 1)).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        OrderContainsKeywordsPredicate predicate = new OrderContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair("Apple Keychain", 1)).build()));

        // Non-matching keyword
        predicate = new OrderContainsKeywordsPredicate(Arrays.asList("Banana"));
        assertFalse(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair("Apple Keychain", 1)).build()));

        // Keywords match phone, email and address, but does not match order name
        predicate = new OrderContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new OrderBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
