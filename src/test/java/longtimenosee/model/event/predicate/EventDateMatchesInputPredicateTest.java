package longtimenosee.model.event.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.EventBuilder;

public class EventDateMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "2022-12-12";
        String secondPredicateInput = "2023-02-15";

        EventDateMatchesInputPredicate firstPredicate = new EventDateMatchesInputPredicate(firstPredicateInput);
        EventDateMatchesInputPredicate secondPredicate = new EventDateMatchesInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal date
        EventDateMatchesInputPredicate firstPredicateCopy = new EventDateMatchesInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal date
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_dateMatchesInput_returnsTrue() {
        // EP: same date
        EventDateMatchesInputPredicate predicate = new EventDateMatchesInputPredicate("2022-12-12");
        assertTrue(predicate.test(new EventBuilder().withDate("2022-12-12").build()));
    }

    @Test
    public void test_dateDoesNotMatchInput_returnsFalse() {
        EventDateMatchesInputPredicate predicate = new EventDateMatchesInputPredicate("2023-12-12");

        // EP: same year, same month, different day
        assertFalse(predicate.test(new EventBuilder().withDate("2023-12-15").build()));

        // EP: same year, different month, same day
        assertFalse(predicate.test(new EventBuilder().withDate("2023-11-12").build()));

        // EP: same year, different month, different day
        assertFalse(predicate.test(new EventBuilder().withDate("2023-10-20").build()));

        // EP: different year, same month, same day
        assertFalse(predicate.test(new EventBuilder().withDate("2024-12-12").build()));

        // EP: different year, same month, different day
        assertFalse(predicate.test(new EventBuilder().withDate("2024-12-25").build()));

        // EP: different year, different month, same day
        assertFalse(predicate.test(new EventBuilder().withDate("2024-05-12").build()));

        // EP: different year, different month, different day
        assertFalse(predicate.test(new EventBuilder().withDate("2025-03-01").build()));
    }
}
