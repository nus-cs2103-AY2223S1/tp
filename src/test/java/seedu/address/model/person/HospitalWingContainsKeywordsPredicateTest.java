package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class HospitalWingContainsKeywordsPredicateTest {

    @Test
    public void test_hospitalWingContainsKeywords_returnsTrue() {
        // One keyword with all lower-case input
        HospitalWingContainsKeywordsPredicate predicate =
                new HospitalWingContainsKeywordsPredicate(Collections.singletonList("south"));
        assertTrue(predicate.test(new PersonBuilder().withHospitalWing("south").build()));

        // one keyword with mixed lower and upper case input
        predicate = new HospitalWingContainsKeywordsPredicate(Collections.singletonList("norTH"));
        assertTrue(predicate.test(new PersonBuilder().withHospitalWing("north").build()));

        // one keyword with all upper case input
        predicate = new HospitalWingContainsKeywordsPredicate(Collections.singletonList("NORTH"));
        assertTrue(predicate.test(new PersonBuilder().withHospitalWing("north").build()));

        // Multiple keywords
        predicate = new HospitalWingContainsKeywordsPredicate(Arrays.asList("north", "south"));
        assertTrue(predicate.test(new PersonBuilder().withHospitalWing("north").build()));

    }

    @Test
    public void test_hospitalWingDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        HospitalWingContainsKeywordsPredicate predicate =
                new HospitalWingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withHospitalWing("east").build()));

        // Non-matching keyword
        predicate = new HospitalWingContainsKeywordsPredicate(Arrays.asList("east"));
        assertFalse(predicate.test(new PersonBuilder().withHospitalWing("north").build()));

        // Keywords match name, email and address, but does not match hospital wing
        predicate = new HospitalWingContainsKeywordsPredicate(
                Arrays.asList("12345678", "alice@email.com", "Alice", "east"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withHospitalWing("south").build()));
    }

    @Test
    public void equalsTest() {
        List<String> firstPredicateKeywordList = Collections.singletonList("south");
        List<String> secondPredicateKeywordList = Arrays.asList("north", "east");

        HospitalWingContainsKeywordsPredicate firstPredicate =
                new HospitalWingContainsKeywordsPredicate(firstPredicateKeywordList);
        HospitalWingContainsKeywordsPredicate secondPredicate =
                new HospitalWingContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        HospitalWingContainsKeywordsPredicate firstPredicateCopy =
                new HospitalWingContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(100));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different hospital wing -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
