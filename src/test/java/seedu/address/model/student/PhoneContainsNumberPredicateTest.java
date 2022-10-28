package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.predicate.PhoneContainsNumberPredicate;
import seedu.address.testutil.PersonBuilder;


public class PhoneContainsNumberPredicateTest {

    private PhoneContainsNumberPredicate predicate = new PhoneContainsNumberPredicate("81234567");

    @Test
    public void equals() {
        String firstPredicateKeyword = "91234567";
        String secondPredicateKeyword = "61234567";

        PhoneContainsNumberPredicate firstPredicate = new PhoneContainsNumberPredicate(firstPredicateKeyword);
        PhoneContainsNumberPredicate secondPredicate = new PhoneContainsNumberPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsNumberPredicate firstPredicateCopy = new PhoneContainsNumberPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsNumber_returnsTrue() {
        assertTrue(predicate.test(new PersonBuilder().withPhone("81234567").build()));
    }

    @Test
    public void test_phoneDoesNotContainNumber_returnsFalse() {
        assertFalse(predicate.test(new PersonBuilder().withPhone("67891234").build()));
    }
}
