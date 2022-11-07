package hobbylist.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.ActivityBuilder;

public class DateMatchesGivenDatePredicateTest {

    @Test
    public void equals() {
        String firstPredicateDateString = "2020-10";
        String secondPredicateDateString = "2022-01-01";

        DateMatchesGivenDatePredicate firstPredicate =
                new DateMatchesGivenDatePredicate(firstPredicateDateString);
        DateMatchesGivenDatePredicate secondPredicate =
                new DateMatchesGivenDatePredicate(secondPredicateDateString);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        DateMatchesGivenDatePredicate firstPredicateCopy =
                new DateMatchesGivenDatePredicate(firstPredicateDateString);
        assertEquals(firstPredicate, firstPredicateCopy);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different activity -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // Matches year
        DateMatchesGivenDatePredicate predicate = new DateMatchesGivenDatePredicate("2022");
        assertTrue(predicate.test(new ActivityBuilder().withDate("2022-01-01").build()));

        // Matches month
        predicate = new DateMatchesGivenDatePredicate("2022-01");
        assertTrue(predicate.test(new ActivityBuilder().withDate("2022-01-01").build()));

        // Matches date
        predicate = new DateMatchesGivenDatePredicate("2022-01-01");
        assertTrue(predicate.test(new ActivityBuilder().withDate("2022-01-01").build()));


    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Default date String (empty)
        DateMatchesGivenDatePredicate predicate = new DateMatchesGivenDatePredicate("");
        assertFalse(predicate.test(new ActivityBuilder().withDate("2022-01-01").build()));

        // Doesn't match year
        predicate = new DateMatchesGivenDatePredicate("2021");
        assertFalse(predicate.test(new ActivityBuilder().withDate("2022-01-01").build()));

        // Matches year but doesn't match month
        predicate = new DateMatchesGivenDatePredicate("2022-02");
        assertFalse(predicate.test(new ActivityBuilder().withDate("2022-01-01").build()));

        // Matches year and month but doesn't match day
        predicate = new DateMatchesGivenDatePredicate("2022-01-02");
        assertFalse(predicate.test(new ActivityBuilder().withDate("2022-01-01").build()));
    }
}
