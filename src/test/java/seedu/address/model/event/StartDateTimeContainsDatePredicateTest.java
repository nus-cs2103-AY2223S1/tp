package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class StartDateTimeContainsDatePredicateTest {
    final DateTime date = new DateTime("11/12/2022");
    final DateTime dateTime = new DateTime("10/10/2021 0000");

    @Test
    public void equals() {
        List<DateTime> firstPredicateDateList = Collections.singletonList(date);
        List<DateTime> secondPredicateDateList = Arrays.asList(date, dateTime);

        StartDateTimeContainsDatePredicate firstPredicate =
                new StartDateTimeContainsDatePredicate(firstPredicateDateList);
        StartDateTimeContainsDatePredicate secondPredicate =
                new StartDateTimeContainsDatePredicate(secondPredicateDateList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StartDateTimeContainsDatePredicate firstPredicateCopy =
                new StartDateTimeContainsDatePredicate(firstPredicateDateList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different profile -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_startDateContainsDates_returnsTrue() {
        // One date
        StartDateTimeContainsDatePredicate predicate =
                new StartDateTimeContainsDatePredicate(Collections.singletonList(date));
        assertTrue(predicate.test(new EventBuilder().withStartDateTime("11/12/2022").build()));

        // Multiple dates
        predicate = new StartDateTimeContainsDatePredicate(Arrays.asList(date, dateTime));
        assertTrue(predicate.test(new EventBuilder().withStartDateTime("11/12/2022 08:00").build()));
    }

    @Test
    public void test_startDateDoesNotContainDate_returnsFalse() {
        // Zero dates
        StartDateTimeContainsDatePredicate predicate =
                new StartDateTimeContainsDatePredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withStartDateTime("11/12/2022 1000").build()));

        // Non-matching dates
        predicate = new StartDateTimeContainsDatePredicate(Arrays.asList(date, dateTime));
        assertFalse(predicate.test(new EventBuilder().withStartDateTime("9/9/2020 1030").build()));

        // Dates match end date, email but does not match name
        predicate = new StartDateTimeContainsDatePredicate(Arrays.asList(date, dateTime));
        assertFalse(predicate.test(new EventBuilder().withStartDateTime("8/10/2022").withEndDateTime("10/10/2022")
                .build()));
    }

}
