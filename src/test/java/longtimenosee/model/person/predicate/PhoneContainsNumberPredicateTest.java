package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PersonBuilder;

public class PhoneContainsNumberPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "87654321";
        String secondPredicateInput = "99825542";

        PhoneContainsNumberPredicate firstPredicate = new PhoneContainsNumberPredicate(firstPredicateInput);
        PhoneContainsNumberPredicate secondPredicate = new PhoneContainsNumberPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal number
        PhoneContainsNumberPredicate firstPredicateCopy = new PhoneContainsNumberPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal number
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsInput_returnsTrue() {
        PhoneContainsNumberPredicate predicate = new PhoneContainsNumberPredicate("8765");

        // EP: same number
        assertTrue(predicate.test(new PersonBuilder().withPhone("8765").build()));

        // EP: phone contains input
        assertTrue(predicate.test(new PersonBuilder().withPhone("87654321").build()));
    }

    @Test
    public void test_phoneDoesNotContainInput_returnsFalse() {
        PhoneContainsNumberPredicate predicate = new PhoneContainsNumberPredicate("8765");

        // EP: phone contains partial input
        assertFalse(predicate.test(new PersonBuilder().withPhone("876").build()));

        // EP: phone does not contain input
        assertFalse(predicate.test(new PersonBuilder().withPhone("1234").build())); // Boundary value
    }
}
