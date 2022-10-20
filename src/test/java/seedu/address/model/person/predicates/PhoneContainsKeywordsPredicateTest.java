package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PhoneContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("999");
        List<String> secondPredicateKeywordList = Arrays.asList("999", "111");

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy = new PhoneContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(
                Collections.singletonList("999"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("999").build()));

        // Multiple keywords
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("111", "999"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("999").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("999").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("123"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("999").build()));

        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("12345", "911", "111", "119"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("999")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
