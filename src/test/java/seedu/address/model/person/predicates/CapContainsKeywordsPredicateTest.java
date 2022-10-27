package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CapContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1");
        List<String> secondPredicateKeywordList = Arrays.asList("1", "2");

        CapContainsKeywordsPredicate firstPredicate = new CapContainsKeywordsPredicate(firstPredicateKeywordList);
        CapContainsKeywordsPredicate secondPredicate = new CapContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CapContainsKeywordsPredicate firstPredicateCopy = new CapContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_capContainsKeywords_returnsTrue() {
        // One keyword
        CapContainsKeywordsPredicate predicate = new CapContainsKeywordsPredicate(
                Collections.singletonList("3.5"));
        assertTrue(predicate.test(new PersonBuilder().withCap(3.5, 5).build()));

        // Only one matching keyword
        predicate = new CapContainsKeywordsPredicate(Arrays.asList("3.5", "4"));
        assertTrue(predicate.test(new PersonBuilder().withCap(3.5, 5).build()));

        // Keyword is converted to double
        predicate = new CapContainsKeywordsPredicate(Collections.singletonList("3"));
        assertTrue(predicate.test(new PersonBuilder().withCap(3.0, 5).build()));
    }

    @Test
    public void test_capDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CapContainsKeywordsPredicate predicate = new CapContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCap(3.5, 5).build()));

        // Non-matching keyword
        predicate = new CapContainsKeywordsPredicate(Arrays.asList("3", "4.0", "5"));
        assertFalse(predicate.test(new PersonBuilder().withCap(3.5, 5).build()));

        // Keywords match phone, and name, but does not match cap
        predicate = new CapContainsKeywordsPredicate(Arrays.asList("100", "12345", "3"));
        assertFalse(predicate.test(new PersonBuilder().withName("100").withPhone("12345")
                .withCap(3.5, 5).build()));
    }
}
