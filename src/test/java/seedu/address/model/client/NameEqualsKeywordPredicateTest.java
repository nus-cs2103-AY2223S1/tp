package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class NameEqualsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        NameEqualsKeywordPredicate firstPredicate = new NameEqualsKeywordPredicate(firstPredicateKeyword);
        NameEqualsKeywordPredicate secondPredicate = new NameEqualsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameEqualsKeywordPredicate firstPredicateCopy = new NameEqualsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameEqualsKeyword_returnsTrue() {
        // One word keyword
        NameEqualsKeywordPredicate predicate = new NameEqualsKeywordPredicate("Alice");
        assertTrue(predicate.test(new ClientBuilder().withName("Alice").build()));

        // Multiple words keyword
        predicate = new NameEqualsKeywordPredicate("Bob The Builder");
        assertTrue(predicate.test(new ClientBuilder().withName("Bob The Builder").build()));

        // One word keyword
        predicate = new NameEqualsKeywordPredicate("Charlie");
        assertTrue(predicate.test(new ClientBuilder().withName("Charlie").build()));

        // Mixed-case keyword
        predicate = new NameEqualsKeywordPredicate("b0bBy");
        assertTrue(predicate.test(new ClientBuilder().withName("b0bBy").build()));
    }

    @Test
    public void test_nameDoesNotEqualKeyword_returnsFalse() {
        // Empty keyword
        NameEqualsKeywordPredicate predicate = new NameEqualsKeywordPredicate("");
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameEqualsKeywordPredicate("Carol");
        assertFalse(predicate.test(new ClientBuilder().withName("Alice Bob").build()));

        predicate = new NameEqualsKeywordPredicate("Dog");
        assertFalse(predicate.test(new ClientBuilder().withName("Alice")
                .withAddress("Main Street").build()));
    }
}
