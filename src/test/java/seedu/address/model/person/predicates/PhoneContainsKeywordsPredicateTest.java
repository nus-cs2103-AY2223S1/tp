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
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

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
                Collections.singletonList("98765432"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Only one matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("98765432", "12345678"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Substring keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("8765"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("12345678")); // diff keyword
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("987654321")); // Longer keyword
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("Alice", "Phone")); // Alphanumeric
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Keywords match name, graduation Date, email and address, but does not match Phone
        predicate = new PhoneContainsKeywordsPredicate(
                Arrays.asList("05-2024", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("98765432")
                .withEmail("alice@email.com").withAddress("Main Street").withGraduationDate("05-2024").build()));
    }
}
