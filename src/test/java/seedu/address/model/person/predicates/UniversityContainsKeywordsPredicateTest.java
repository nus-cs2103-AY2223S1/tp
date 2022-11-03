package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class UniversityContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        UniversityContainsKeywordsPredicate firstPredicate = new UniversityContainsKeywordsPredicate(
                firstPredicateKeywordList);
        UniversityContainsKeywordsPredicate secondPredicate = new UniversityContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        UniversityContainsKeywordsPredicate firstPredicateCopy = new UniversityContainsKeywordsPredicate(
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
    public void test_universityContainsKeywords_returnsTrue() {
        // One keyword
        UniversityContainsKeywordsPredicate predicate = new UniversityContainsKeywordsPredicate(
                Collections.singletonList("University"));
        assertTrue(predicate.test(new PersonBuilder().withUniversity("National University of Tango Sierra").build()));

        // Multiple matching keywords
        predicate = new UniversityContainsKeywordsPredicate(Arrays.asList("National", "University", "Tango"));
        assertTrue(predicate.test(new PersonBuilder().withUniversity("National University of Tango Sierra").build()));

        // Only one matching keyword
        predicate = new UniversityContainsKeywordsPredicate(Arrays.asList("Tango", "Zula"));
        assertTrue(predicate.test(new PersonBuilder().withUniversity("National University of Tango Sierra").build()));

        // Mixed-case keywords
        predicate = new UniversityContainsKeywordsPredicate(Arrays.asList("TaNgO", "ZuLA"));
        assertTrue(predicate.test(new PersonBuilder().withUniversity("National University of Tango Sierra").build()));
    }

    @Test
    public void test_universityDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        UniversityContainsKeywordsPredicate predicate = new UniversityContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withUniversity("National University of Tango Sierra").build()));

        // Non-matching keyword
        predicate = new UniversityContainsKeywordsPredicate(Arrays.asList("NUTS"));
        assertFalse(predicate.test(new PersonBuilder().withUniversity("National University of Tango Sierra").build()));

        // Keywords are substrings
        predicate = new UniversityContainsKeywordsPredicate(Arrays.asList("Nat", "Uni"));
        assertFalse(predicate.test(new PersonBuilder().withUniversity("National University of Tango Sierra").build()));

        // Keywords match name, phone, email and address, but does not match University
        predicate = new UniversityContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withUniversity("NUTS").build()));
    }
}
