package seedu.boba.model.customer;

import org.junit.jupiter.api.Test;
import seedu.boba.testutil.CustomerBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllInfoContainsKeywordsTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AllInfoContainsKeywordsPredicate firstPredicate = new AllInfoContainsKeywordsPredicate(firstPredicateKeywordList);
        AllInfoContainsKeywordsPredicate secondPredicate = new AllInfoContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AllInfoContainsKeywordsPredicate firstPredicateCopy = new AllInfoContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different customer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        AllInfoContainsKeywordsPredicate predicate = new AllInfoContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new AllInfoContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new AllInfoContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new AllInfoContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AllInfoContainsKeywordsPredicate predicate = new AllInfoContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new AllInfoContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new AllInfoContainsKeywordsPredicate(Arrays.asList("12345678", "alice@email.com", "420"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withReward("420").build()));
    }
}
