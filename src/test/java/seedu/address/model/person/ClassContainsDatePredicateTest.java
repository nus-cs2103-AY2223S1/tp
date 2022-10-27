package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicate.ClassContainsDatePredicate;
import seedu.address.testutil.PersonBuilder;

public class ClassContainsDatePredicateTest {

    private ClassContainsDatePredicate predicate = new ClassContainsDatePredicate("2022-10-12");

    @Test
    public void equals() {
        String firstPredicateKeyword = "2022-10-20";
        String secondPredicateKeyword = "2022-10-15";

        ClassContainsDatePredicate firstPredicate = new ClassContainsDatePredicate(firstPredicateKeyword);
        ClassContainsDatePredicate secondPredicate = new ClassContainsDatePredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClassContainsDatePredicate firstPredicateCopy = new ClassContainsDatePredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_classContainsKeyword_returnsTrue() throws ParseException {
        assertTrue(predicate.test(new PersonBuilder().withClass("2022-10-12 1000-1200").build()));
    }

    @Test
    public void test_classDoesNotContainKeyword_returnsFalse() throws ParseException {
        assertFalse(predicate.test(new PersonBuilder().withClass("2022-10-13 1000-1200").build()));
    }
}
