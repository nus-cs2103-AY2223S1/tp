package seedu.boba.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.boba.testutil.CustomerBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class NameSoundsSimilarToPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameSoundsSimilarToPredicate firstPredicate = new NameSoundsSimilarToPredicate(firstPredicateKeywordList);
        NameSoundsSimilarToPredicate secondPredicate = new NameSoundsSimilarToPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameSoundsSimilarToPredicate firstPredicateCopy = new NameSoundsSimilarToPredicate(firstPredicateKeywordList);
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
        NameSoundsSimilarToPredicate predicate = new NameSoundsSimilarToPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameSoundsSimilarTo_returnsTrue() {
        NameSoundsSimilarToPredicate predicate = new NameSoundsSimilarToPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alise").build()));

        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("Aschcroft"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Ashcraft").build()));

        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("Alexander"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alexandra").build()));

        // Mixed-case keywords
        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("aLIce"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameSoundsSimilarToPredicate predicate = new NameSoundsSimilarToPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameSoundsSimilarToPredicate(Arrays.asList("12345678", "alice@email.com", "420"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withReward("420").build()));
    }
}
