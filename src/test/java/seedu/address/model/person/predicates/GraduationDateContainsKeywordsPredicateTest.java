package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GraduationDateContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GraduationDateContainsKeywordsPredicate firstPredicate = new GraduationDateContainsKeywordsPredicate(
                firstPredicateKeywordList);
        GraduationDateContainsKeywordsPredicate secondPredicate = new GraduationDateContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GraduationDateContainsKeywordsPredicate firstPredicateCopy = new GraduationDateContainsKeywordsPredicate(
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
    public void test_graduationDateContainsKeywords_returnsTrue() {
        // One keyword
        GraduationDateContainsKeywordsPredicate predicate = new GraduationDateContainsKeywordsPredicate(
                Collections.singletonList("05-2024"));
        assertTrue(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));

        // Only one matching keyword
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("05-2024", "06-2024"));
        assertTrue(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));

    }

    @Test
    public void test_graduationDateDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GraduationDateContainsKeywordsPredicate predicate = new GraduationDateContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));

        // Non-matching keyword
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("12-2040")); // diff keyword
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("05-2025")); // month but not year
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("06-2024")); // year but not month
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("052024")); // no separator
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("2024-05")); // opposite format
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("12-05-2024")); // keyword larger
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));
        predicate = new GraduationDateContainsKeywordsPredicate(Arrays.asList("May")); // Alphanumeric
        assertFalse(predicate.test(new PersonBuilder().withGraduationDate("05-2024").build()));

        // Keywords match name, phone, email and address, but does not match GraduationDate
        predicate = new GraduationDateContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withGraduationDate("05-2024").build()));
    }
}
