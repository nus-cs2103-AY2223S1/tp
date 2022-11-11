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

public class OrderMatchesFlagsAndPrefixPredicateTest {
    private static final List<String> EMPTY_LIST = Collections.emptyList();

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
    public void test_orderNameContainsKeywords_returnTrue() {
        Order order1 = new OrderBuilder()
                .withName("Alice Tan").build();
        Order order2 = new OrderBuilder()
                .withName("Brandon Tan").build();

        // One keyword
        OrderMatchesFlagsAndPrefixPredicate predicate =
                new OrderMatchesFlagsAndPrefixPredicate(Collections.singletonList("Alice"), EMPTY_LIST, EMPTY_LIST,
                        false, false, false, false);
        assertTrue(predicate.test(order1));

        // Multiple keywords
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice", "Tan"), EMPTY_LIST, EMPTY_LIST,
                        false, false, false, false);
        assertTrue(predicate.test(order1));

        // Only one matching keyword
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice", "Tan"), EMPTY_LIST, EMPTY_LIST,
                false, false, false, false);
        assertTrue(predicate.test(order2));

        // Mixed case keyword
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("aLiCe", "tAn"), EMPTY_LIST, EMPTY_LIST,
                false, false, false, false);
        assertTrue(predicate.test(order2));
    }

    @Test
    public void test_orderAddressContainsKeywords_returnTrue() {
        Order order1 = new OrderBuilder()
                .withAddress("75 Clementi Street, Blk 990, #45-09").build();
        Order order2 = new OrderBuilder()
                .withAddress("23 King Albert Park Street, 632093").build();

        // One keyword
        OrderMatchesFlagsAndPrefixPredicate predicate =
                new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, Collections.singletonList("Clementi"),
                        EMPTY_LIST, false, false, false, false);
        assertTrue(predicate.test(order1));

        // Multiple keywords
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, Arrays.asList("Clementi", "Street"), EMPTY_LIST,
                false, false, false, false);
        assertTrue(predicate.test(order1));

        // Only one matching keyword
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, Arrays.asList("Albert", "Blk"), EMPTY_LIST,
                false, false, false, false);
        assertTrue(predicate.test(order2));

        // Mixed case keyword
        // Multiple keywords
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, Arrays.asList("albErT", "pArk"), EMPTY_LIST,
                false, false, false, false);
        assertTrue(predicate.test(order2));
    }

    // contains item keywords
    @Test
    public void test_orderItemContainsKeywords_returnsTrue() {
        InventoryItem appleKeychainItem = new InventoryItem(new ItemName("Apple Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));
        InventoryItem bananaKeychainItem = new InventoryItem(new ItemName("Banana Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));

        // One keyword
        OrderMatchesFlagsAndPrefixPredicate predicate =
                new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, EMPTY_LIST,
                        Collections.singletonList("Keychain"),
                        false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(appleKeychainItem, new Quantity(1))).build()));

        // Multiple keywords
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, EMPTY_LIST,
                Arrays.asList("Keychain", "Apple"), false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(appleKeychainItem, new Quantity(1))).build()));

        // Only one matching keyword
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, EMPTY_LIST,
                Arrays.asList("Keychain", "Apple"), false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(bananaKeychainItem, new Quantity(1))).build()));

        // Mixed-case keywords
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, EMPTY_LIST,
                Arrays.asList("kEyChAiN", "aPpLe"), false, false, false, false);
        assertTrue(predicate.test(new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(bananaKeychainItem, new Quantity(1))).build()));
    }

    @Test
    public void test_orderContainsKeywordsFromMultipleFields_returnsTrue() {
        InventoryItem appleKeychainItem = new InventoryItem(new ItemName("Apple Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));
        InventoryItem bananaKeychainItem = new InventoryItem(new ItemName("Banana Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));

        // name + item
        // one keyword each
        Order order1 = new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(appleKeychainItem, new Quantity(1)))
                .withName("Alice Tan")
                .withAddress("75 Clementi Street, Blk 990, #45-09")
                .withDeliveredStatus(true)
                .withPaidStatus(true).build();
        Order order2 = new OrderBuilder().withItemQuantityPair(
                new ItemQuantityPair(bananaKeychainItem, new Quantity(1)))
                .withName("Bobbert Bobbertson")
                .withAddress("23 King Albert Park Street, 632093").build();
        OrderMatchesFlagsAndPrefixPredicate predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice"),
                EMPTY_LIST, Arrays.asList("Apple"), true, true, true, true);
        assertTrue(predicate.test(order1));

        // 2 keywords each
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice", "Tan"),
                EMPTY_LIST, Arrays.asList("Apple", "Keychain"), false, true, false, true);
        assertTrue(predicate.test(order1));

        // name + address
        // one keyword each
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice"),
                Arrays.asList("Clementi"), EMPTY_LIST, true, false, true, false);
        assertTrue(predicate.test(order1));

        // 2 keywords each
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Bobbert", "Bobbetson"),
                EMPTY_LIST, Arrays.asList("Banana", "Keychain"), false, false, false, false);
        assertTrue(predicate.test(order2));

        // address + item
        // one keyword each
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST,
                Arrays.asList("Clementi"), Arrays.asList("Apple"), false, false, false, false);
        assertTrue(predicate.test(order1));

        // 2 keywords each
        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST,
                Arrays.asList("Clementi", "Street"), Arrays.asList("Apple", "Keychain"), false, false, false, false);
        assertTrue(predicate.test(order1));
    }

    @Test
    public void test_orderContainsKeywordsFromMultipleFields_returnsFalse() {
        InventoryItem appleKeychainItem = new InventoryItem(new ItemName("Apple Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));
        InventoryItem bananaKeychainItem = new InventoryItem(new ItemName("Banana Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));

        // name + item
        // one keyword matches, one doesnt
        Order order1 = new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(appleKeychainItem, new Quantity(1)))
                .withName("Alice Tan").build();
        Order order2 = new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(bananaKeychainItem, new Quantity(1)))
                .withName("Brendan").build();
        OrderMatchesFlagsAndPrefixPredicate predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice"),
                EMPTY_LIST, Arrays.asList("Banana"), false, false, false, false);
        assertFalse(predicate.test(order1));

        // both keywords don't match
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Bobby"),
                EMPTY_LIST, Arrays.asList("Apple"), false, false, false, false);
        assertFalse(predicate.test(order2));
    }

    @Test
    public void test_orderMatchesKeywordInAllFields_returnsTrue() {
        InventoryItem appleKeychainItem = new InventoryItem(new ItemName("Apple Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));
        Order order1 = new OrderBuilder()
                .withAddress("75 Clementi Street, Blk 990, #45-09")
                .withName("Alice Tan")
                .withItemQuantityPair(new ItemQuantityPair(appleKeychainItem, new Quantity(4))).build();
        OrderMatchesFlagsAndPrefixPredicate predicate = new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice"),
                Arrays.asList("Clementi"), Arrays.asList("Apple"), false, false, false, false);
        assertTrue(predicate.test(order1));
    }

    @Test
    public void test_orderMatchesKeywordInAllFields_returnsFalse() {
        InventoryItem appleKeychainItem = new InventoryItem(new ItemName("Apple Keychain"), new Description("test"),
                new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));
        Order order1 = new OrderBuilder()
                .withAddress("75 Clementi Street, Blk 990, #45-09")
                .withName("Alice Tan")
                .withItemQuantityPair(new ItemQuantityPair(appleKeychainItem, new Quantity(4))).build();
        OrderMatchesFlagsAndPrefixPredicate predicate = new OrderMatchesFlagsAndPrefixPredicate(
                Arrays.asList("Bobbert"), Arrays.asList("Clementi"), Arrays.asList("Apple"),
                false, false, false, false);
        assertFalse(predicate.test(order1));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        InventoryItem appleKeychainItem = new InventoryItem(new ItemName("Apple Keychain"), new Description("test"),
            new Quantity(300), new HashSet<>(), new Price(2.00), new Price(5.00));

        // Zero keywords
        OrderMatchesFlagsAndPrefixPredicate predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(), false, false, false, false);
        assertFalse(predicate.test(new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(appleKeychainItem, new Quantity(1))).build()));

        // Non-matching keyword
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("Banana"), false, false, false, false);
        assertFalse(predicate.test(new OrderBuilder()
                .withItemQuantityPair(new ItemQuantityPair(appleKeychainItem, new Quantity(1))).build()));

        // Keywords match phone, email and address, but does not match order name
        predicate = new OrderMatchesFlagsAndPrefixPredicate(Collections.emptyList(), Collections.emptyList(),
                Arrays.asList("12345", "alice@email.com", "Main", "Street"),
                false, false, false, false);
        assertFalse(predicate.test(new OrderBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street").build()));
    }

    @Test
    public void test_orderMatchesFlags() {
        Order order1 = new OrderBuilder()
                .withDeliveredStatus(true)
                .withPaidStatus(true).build();
        OrderMatchesFlagsAndPrefixPredicate predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, EMPTY_LIST,
                EMPTY_LIST, true, true, true, true);
        assertTrue(predicate.test(order1));

        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, EMPTY_LIST,
                EMPTY_LIST, false, true, true, true);
        assertTrue(predicate.test(order1));

        predicate = new OrderMatchesFlagsAndPrefixPredicate(EMPTY_LIST, EMPTY_LIST,
                EMPTY_LIST, true, false, true, false);
        assertTrue(predicate.test(order1));

    }
}
