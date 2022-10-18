package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class DayIsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        DayIsKeywordPredicate firstPredicate = new DayIsKeywordPredicate(firstPredicateKeyword);
        DayIsKeywordPredicate secondPredicate = new DayIsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DayIsKeywordPredicate firstPredicateCopy = new DayIsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_dayIsKeyword_returnsTrue() {
        // One keyword
        DayIsKeywordPredicate predicate = new DayIsKeywordPredicate("Mon");
        assertTrue(predicate.test(new PersonBuilder().withSession("Mon 08:00").build()));

        // Only one matching keyword
        predicate = new DayIsKeywordPredicate("Tue");
        assertTrue(predicate.test(new PersonBuilder().withSession("Mon 08:00", "Tue 12:00").build()));

        // Mixed-case keywords
        predicate = new DayIsKeywordPredicate("mOn");
        assertTrue(predicate.test(new PersonBuilder().withSession("Mon 08:00").build()));
    }

    @Test
    public void test_dayIsNotKeyword_returnsFalse() {
        // Zero keywords
        DayIsKeywordPredicate predicate = new DayIsKeywordPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withSession("Mon 08:00").build()));

        // Non-matching keyword
        predicate = new DayIsKeywordPredicate("Mon");
        assertFalse(predicate.test(new PersonBuilder().withSession("Tue 08:00").build()));

        // Keywords match name, but does not match session day
        predicate = new DayIsKeywordPredicate("Mon");
        assertFalse(predicate.test(new PersonBuilder().withName("Mon").withSession("Tue 09:30").build()));
    }
}
