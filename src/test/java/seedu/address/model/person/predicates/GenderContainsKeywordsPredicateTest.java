package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GenderContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GenderContainsKeywordsPredicate firstPredicate = new GenderContainsKeywordsPredicate(
                firstPredicateKeywordList);
        GenderContainsKeywordsPredicate secondPredicate = new GenderContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenderContainsKeywordsPredicate firstPredicateCopy = new GenderContainsKeywordsPredicate(
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
    public void test_genderContainsKeywords_returnsTrue() {
        // One keyword
        GenderContainsKeywordsPredicate predicate = new GenderContainsKeywordsPredicate(
                Collections.singletonList("male"));
        assertTrue(predicate.test(new PersonBuilder().withGender("male").build()));

        // Only one matching keyword
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("male", "female"));
        assertTrue(predicate.test(new PersonBuilder().withGender("male").build()));

        // Mixed-case keywords
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("MaLe", "fEMale"));
        assertTrue(predicate.test(new PersonBuilder().withGender("female").build()));
    }

    @Test
    public void test_genderDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GenderContainsKeywordsPredicate predicate = new GenderContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withGender("female").build()));

        // Non-matching keyword
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("M"));
        assertFalse(predicate.test(new PersonBuilder().withGender("male").build()));
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("Fe"));
        assertFalse(predicate.test(new PersonBuilder().withGender("Female").build()));

        // Keywords match name, phone, email and address, but does not match Gender
        predicate = new GenderContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withGender("male").build()));
    }
}
