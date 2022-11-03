package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class BuyerNameContainsSubstringPredicateTest {

    @Test
    public void equals() {
        String firstPredicateString = "first";
        String secondPredicateString = "first second";

        BuyerNameContainsSubstringPredicate firstPredicate =
                new BuyerNameContainsSubstringPredicate(firstPredicateString);
        BuyerNameContainsSubstringPredicate secondPredicate =
                new BuyerNameContainsSubstringPredicate(secondPredicateString);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BuyerNameContainsSubstringPredicate firstPredicateCopy =
                new BuyerNameContainsSubstringPredicate(firstPredicateString);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different buyer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsSubstring_returnsTrue() {
        BuyerNameContainsSubstringPredicate predicate;

        // Mixed-case substring of one word
        predicate = new BuyerNameContainsSubstringPredicate("aLiC");
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        // Mixed-case substring across words
        predicate = new BuyerNameContainsSubstringPredicate("aLiCe b");
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainSubstring_returnsFalse() {
        BuyerNameContainsSubstringPredicate predicate;

        predicate = new BuyerNameContainsSubstringPredicate("aLiC l");
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        predicate = new BuyerNameContainsSubstringPredicate("aLiCm");
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new BuyerNameContainsSubstringPredicate("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
