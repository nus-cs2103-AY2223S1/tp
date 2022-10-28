package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.predicate.NokPhoneContainsNumberPredicate;
import seedu.address.testutil.PersonBuilder;

public class NokPhoneContainsNumberPredicateTest {

    private NokPhoneContainsNumberPredicate predicate = new NokPhoneContainsNumberPredicate("81234566");

    @Test
    public void equals() {
        String firstPredicateKeyword = "91234566";
        String secondPredicateKeyword = "61234566";

        NokPhoneContainsNumberPredicate firstPredicate = new NokPhoneContainsNumberPredicate(firstPredicateKeyword);
        NokPhoneContainsNumberPredicate secondPredicate = new NokPhoneContainsNumberPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NokPhoneContainsNumberPredicate firstPredicateCopy = new NokPhoneContainsNumberPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nokPhoneContainsNumber_returnsTrue() {
        assertTrue(predicate.test(new PersonBuilder().withNokPhone("81234566").build()));
    }

    @Test
    public void test_nokPhoneDoesNotContainNumber_returnsFalse() {
        assertFalse(predicate.test(new PersonBuilder().withNokPhone("67891234").build()));
    }
}
