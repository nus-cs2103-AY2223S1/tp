package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class MeetingTimePastPredicateTest {

    @Test
    void test_earlierThanPresent_returnsTrue() {

        MeetingTimePastPredicate predicate = new MeetingTimePastPredicate();
        LocalDateTime curTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm");

        // Hours past
        String oneHourEarlier = curTime.minusHours(1).format(formatter);
        assertTrue(predicate.test(new MeetingTime(oneHourEarlier)));

        // Days past
        String threeDaysEarlier = curTime.minusDays(3).format(formatter);
        assertTrue(predicate.test(new MeetingTime(threeDaysEarlier)));

        // Minutes past
        String fourMinutesEarlier = curTime.minusMinutes(4).format(formatter);
        assertTrue(predicate.test(new MeetingTime(fourMinutesEarlier)));
    }

    @Test
    void test_notEarlierThanPresent_returnsFalse() {

        MeetingTimePastPredicate predicate = new MeetingTimePastPredicate();
        LocalDateTime curTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm");

        // Hours in the future
        String oneHourLater = curTime.plusHours(1).format(formatter);
        assertFalse(predicate.test(new MeetingTime(oneHourLater)));

        // Days in the future
        String threeDaysLater = curTime.plusDays(3).format(formatter);
        assertFalse(predicate.test(new MeetingTime(threeDaysLater)));

        // Minutes in the future
        String fourMinutesLater = curTime.plusMinutes(4).format(formatter);
        assertFalse(predicate.test(new MeetingTime(fourMinutesLater)));
    }

    @Test
    void testEquals() {
        MeetingTimePastPredicate predicate = new MeetingTimePastPredicate();

        // same object -> returns true
        assertEquals(predicate, predicate);

        // different types -> returns false
        assertNotEquals(1, predicate);

        // null -> returns false
        assertNotEquals(null, predicate);

    }
}
