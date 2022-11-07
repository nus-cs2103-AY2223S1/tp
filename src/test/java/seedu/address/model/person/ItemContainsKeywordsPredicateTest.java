package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ItemContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstItemPredicateKeywordList = Collections.singletonList("item1");
        List<String> secondItemPredicateKeywordList = Arrays.asList("item1", "item2");

        ItemContainsKeywordsPredicate firstItemPredicate =
                new ItemContainsKeywordsPredicate(firstItemPredicateKeywordList);
        ItemContainsKeywordsPredicate secondItemPredicate =
                new ItemContainsKeywordsPredicate(secondItemPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstItemPredicate.equals(firstItemPredicate));

        // same values -> returns true
        ItemContainsKeywordsPredicate firstItemPredicateCopy =
                new ItemContainsKeywordsPredicate(firstItemPredicateKeywordList);
        assertTrue(firstItemPredicate.equals(firstItemPredicateCopy));

        // different types -> returns false
        assertFalse(firstItemPredicate.equals(1));

        // null -> returns false
        assertFalse(firstItemPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstItemPredicate.equals(secondItemPredicate));
    }

    @Test
    public void test_itemContainsKeywords_returnsTrue() {
        // One keyword
        ItemContainsKeywordsPredicate predicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("Chicken"));
        assertTrue(predicate.test(new PersonBuilder().withItem("Chicken Egg").build()));

        // Multiple keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Chicken", "Egg"));
        assertTrue(predicate.test(new PersonBuilder().withItem("Chicken Egg").build()));

        // Only one matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Chicken", "Egg"));
        assertTrue(predicate.test(new PersonBuilder().withItem("Chicken Beef").build()));

        // Mixed-case keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("cHiCken", "eGg"));
        assertTrue(predicate.test(new PersonBuilder().withItem("Chicken Egg").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemContainsKeywordsPredicate predicate = new ItemContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withItem("Chicken").build()));

        // Non-matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Water"));
        assertFalse(predicate.test(new PersonBuilder().withName("Chicken Egg").build()));

        // Keywords match name, phone, price and address, but does not match item
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("12345", "$1.20", "Alice", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withPrice("$1.20").withItem("Chicken").withAddress("Main Street").build()));
    }
}
