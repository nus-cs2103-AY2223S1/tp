package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingFilterPredicateTest {

    @Test
    public void equals() {
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 8, 10, 12);
        LocalDateTime date2 = LocalDateTime.of(2021, 2, 11, 1, 11);
        MeetingFilterDatePredicate firstPredicate = new MeetingFilterDatePredicate(date1, date2);
        MeetingFilterDatePredicate secondPredicate = new MeetingFilterDatePredicate(date2, date2);
        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        MeetingFilterDatePredicate firstPredicateCopy = new MeetingFilterDatePredicate(date1, date2);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_meetingFilterFindMeeting_returnsTrue() {
        LocalDateTime date1 = LocalDateTime.of(2022, 10, 10, 10, 10);
        LocalDateTime date2 = LocalDateTime.of(2023, 2, 11, 1, 11);
        MeetingFilterDatePredicate firstPredicate = new MeetingFilterDatePredicate(date1, date2);
        MeetingFilterDatePredicate secondPredicate = new MeetingFilterDatePredicate(date1, date1);
        MeetingFilterDatePredicate thirdPredicate = new MeetingFilterDatePredicate(date2, date1);
        // Normal Case
        assertTrue(firstPredicate.test(new MeetingBuilder()
                .withDateAndTime("Wednesday, 26 October 2022 10:10 AM").build()));

        // Same Dates Case
        assertTrue(secondPredicate.test(new MeetingBuilder()
                .withDateAndTime("Monday, 10 October 2022 10:10 AM").build()));

        // Error Case
        assertFalse(thirdPredicate.test(new MeetingBuilder()
                .withDateAndTime("Wednesday, 26 October 2022 10:10 AM").build()));

    }

}
