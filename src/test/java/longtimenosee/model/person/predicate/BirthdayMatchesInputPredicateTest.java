package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PersonBuilder;

public class BirthdayMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "2001-02-05";
        String secondPredicateInput = "1990-07-12";

        BirthdayMatchesInputPredicate firstPredicate = new BirthdayMatchesInputPredicate(firstPredicateInput);
        BirthdayMatchesInputPredicate secondPredicate = new BirthdayMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal date
        BirthdayMatchesInputPredicate firstPredicateCopy = new BirthdayMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal date
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_birthdayMatchesInput_returnsTrue() {
        // EP: same date
        BirthdayMatchesInputPredicate predicate = new BirthdayMatchesInputPredicate("1990-07-12");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("1990-07-12").build()));
    }

    @Test
    public void test_birthdayDoesNotMatchInput_returnsFalse() {
        BirthdayMatchesInputPredicate predicate = new BirthdayMatchesInputPredicate("1990-07-12");

        // EP: same year, same month, different day
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1990-07-30").build()));

        // EP: same year, different month, same day
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1990-01-12").build()));

        // EP: same year, different month, different day
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1990-05-25").build()));

        // EP: different year, same month, same day
        assertFalse(predicate.test(new PersonBuilder().withBirthday("2000-07-12").build()));

        // EP: different year, same month, different day
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1985-07-15").build()));

        // EP: different year, different month, same day
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1992-12-12").build()));

        // EP: different year, different month, different day
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1985-02-20").build()));
    }
}
