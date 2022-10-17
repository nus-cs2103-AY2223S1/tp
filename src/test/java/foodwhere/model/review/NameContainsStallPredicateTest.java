package foodwhere.model.review;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import foodwhere.testutil.ReviewBuilder;

public class NameContainsStallPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        List<String> thirdPredicateKeywordList = Arrays.asList("second", "first");

        NameContainsStallPredicate firstPredicate = new NameContainsStallPredicate(firstPredicateKeywordList);
        NameContainsStallPredicate secondPredicate = new NameContainsStallPredicate(secondPredicateKeywordList);
        NameContainsStallPredicate thirdPredicate = new NameContainsStallPredicate(thirdPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsStallPredicate firstPredicateCopy = new NameContainsStallPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        //same keywords, different order -> returns true
        assertTrue(secondPredicate.equals(thirdPredicate));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different stall -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsStall_returnsTrue() {
        // One keyword
        NameContainsStallPredicate predicate = new NameContainsStallPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsStallPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsStallPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsStallPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainsStall_returnsFalse() {
        // Zero keywords
        NameContainsStallPredicate predicate = new NameContainsStallPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ReviewBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsStallPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));

        // Keywords match content, but does not match name
        predicate = new NameContainsStallPredicate(Arrays.asList("Main", "Street"));
        assertFalse(predicate.test(new ReviewBuilder().withName("Alice")
                .withContent("Main Street").build()));
    }
}
