package tracko.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.testutil.OrderBuilder;

public class OrderContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateNameKeywordList = Collections.singletonList("Alice");
        List<String> firstPredicateAddressKeywordList = Collections.singletonList("Clementi");
        List<String> firstPredicateItemKeywordList = Collections.singletonList("Keychain");
        Boolean firstPredicateIsFilteringByPaid = false;
        Boolean firstPredicateIsFilteringByDelivered = true;
        Boolean firstPredicateIsPaid = false;
        Boolean firstPredicateIsDelivered = true;
        List<String> secondPredicateNameKeywordList = Arrays.asList("Alice", "Bob");
        List<String> secondPredicateAddressKeywordList = Collections.singletonList("Clementi, Geylang");
        List<String> secondPredicateItemKeywordList = Collections.singletonList("keychain, pillow");
        Boolean secondPredicateIsFilteringByPaid = false;
        Boolean secondPredicateIsFilteringByDelivered = false;
        Boolean secondPredicateIsPaid = false;
        Boolean secondPredicateIsDelivered = false;

        OrderMatchesFlagsAndPrefixPredicate firstPredicate =
                new OrderMatchesFlagsAndPrefixPredicate(firstPredicateNameKeywordList,
                        firstPredicateAddressKeywordList, firstPredicateItemKeywordList,
                        firstPredicateIsFilteringByPaid, firstPredicateIsFilteringByDelivered,
                        firstPredicateIsPaid, firstPredicateIsDelivered);
        OrderMatchesFlagsAndPrefixPredicate secondPredicate =
                new OrderMatchesFlagsAndPrefixPredicate(secondPredicateNameKeywordList,
                        secondPredicateAddressKeywordList, secondPredicateItemKeywordList,
                        secondPredicateIsFilteringByPaid, secondPredicateIsFilteringByDelivered,
                        secondPredicateIsPaid, secondPredicateIsDelivered);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderMatchesFlagsAndPrefixPredicate firstPredicateCopy =
                new OrderMatchesFlagsAndPrefixPredicate(firstPredicateNameKeywordList,
                        firstPredicateAddressKeywordList, firstPredicateItemKeywordList,
                        firstPredicateIsFilteringByPaid, firstPredicateIsFilteringByDelivered,
                        firstPredicateIsPaid, firstPredicateIsDelivered);
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
        InventoryItem appleKeychainInventoryItem = new InventoryItem(
            new ItemName("Apple Keychain"), new Description("test"), new Quantity(300),
                new HashSet<>(), new Price(2.00), new Price(5.00));
        InventoryItem bananaKeychainInventoryItem = new InventoryItem(
            new ItemName("Banana Keychain"), new Description("test"), new Quantity(300),
                new HashSet<>(), new Price(2.00), new Price(5.00));

        // One keyword
        OrderMatchesFlagsAndPrefixPredicate predicate =
                new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                        Collections.singletonList("Keychain"), false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(appleKeychainInventoryItem, new Quantity(1))).build()));

        // Multiple keywords
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("Keychain", "Apple"), false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(appleKeychainInventoryItem, new Quantity(1))).build()));

        // Only one matching keyword
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("Keychain", "Apple"), false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(bananaKeychainInventoryItem, new Quantity(1))).build()));

        // Mixed-case keywords
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("kEyChAiN", "aPpLe"), false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(bananaKeychainInventoryItem, new Quantity(1))).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        InventoryItem appleKeychainInventoryItem = new InventoryItem(
            new ItemName("Apple Keychain"), new Description("test"), new Quantity(300),
                new HashSet<>(), new Price(2.00), new Price(5.00));
        InventoryItem bananaKeychainInventoryItem = new InventoryItem(
            new ItemName("Banana Keychain"), new Description("test"), new Quantity(300),
                new HashSet<>(), new Price(1.99), new Price(4.85));

        // Zero keywords
        OrderMatchesFlagsAndPrefixPredicate predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(), false, false, false, false);
        assertFalse(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(appleKeychainInventoryItem, new Quantity(1))).build()));

        // Non-matching keyword
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("Banana"), false, false, false, false);
        assertFalse(predicate.test(new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(appleKeychainInventoryItem, new Quantity(1))).build()));

        // Keywords match phone, email and address, but does not match order name
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("12345", "alice@email.com", "Main", "Street"),
                false, false, false, false);
        assertFalse(predicate.test(new OrderBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
