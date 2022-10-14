package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Appointment;
import seedu.address.testutil.AppointmentBuilder;

class DateTimeWithinRangePredicateTest {

    private final LocalDateTime targetTime = LocalDateTime.parse("2000-12-31T12:34");
    private final LocalDateTime beforeTargetTime = LocalDateTime.parse("1900-01-01T00:00");
    private final LocalDateTime afterTargetTime = LocalDateTime.parse("2100-01-01T00:00");
    private final LocalDateTime minTime = LocalDateTime.MIN;
    private final LocalDateTime maxTime = LocalDateTime.MAX;

    @Test
    public void equals() {
        LocalDateTime firstDateTimeStart = LocalDateTime.parse("2000-12-12T00:00");
        LocalDateTime firstDateTimeEnd = LocalDateTime.parse("2050-06-06T12:34");
        LocalDateTime secondDateTimeStart = LocalDateTime.parse("2100-01-01T23:59");
        LocalDateTime secondDateTimeEnd = LocalDateTime.parse("2150-12-31T13:45");

        DateTimeWithinRangePredicate firstPredicate =
                new DateTimeWithinRangePredicate(firstDateTimeStart, firstDateTimeEnd);
        DateTimeWithinRangePredicate firstPredicateCopy =
                new DateTimeWithinRangePredicate(firstDateTimeStart, firstDateTimeEnd);
        DateTimeWithinRangePredicate secondPredicate =
                new DateTimeWithinRangePredicate(secondDateTimeStart, secondDateTimeEnd);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);

        LocalDateTime differentDateTime = LocalDateTime.parse("2000-12-12T00:01");

        // Different start time but same end time -> Not equals
        DateTimeWithinRangePredicate differentDateTimeStartPredicate =
                new DateTimeWithinRangePredicate(differentDateTime, firstDateTimeEnd);
        assertNotEquals(differentDateTimeStartPredicate, firstPredicate);

        // Different end time but same start time -> Not equals
        DateTimeWithinRangePredicate differentDateTimeEndPredicate =
                new DateTimeWithinRangePredicate(firstDateTimeStart, differentDateTime);
        assertNotEquals(differentDateTimeEndPredicate, firstPredicate);
    }

    @Test
    public void test_withinDateTimeRange_returnsTrue() {
        Appointment appointmentToTest = generateAppointmentWithDateTime(targetTime);

        // Within range
        DateTimeWithinRangePredicate predicate =
                new DateTimeWithinRangePredicate(beforeTargetTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Same dateTime with startDateTime
        predicate = new DateTimeWithinRangePredicate(targetTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Same dateTime with endDateTime
        predicate = new DateTimeWithinRangePredicate(beforeTargetTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Exact same time
        predicate = new DateTimeWithinRangePredicate(targetTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no startTime restriction
        predicate = new DateTimeWithinRangePredicate(minTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no endTime restriction
        predicate = new DateTimeWithinRangePredicate(beforeTargetTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no startTime restriction, same time as endTime
        predicate = new DateTimeWithinRangePredicate(minTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no endTime restriction, same time as startTime
        predicate = new DateTimeWithinRangePredicate(targetTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));

        // No restrictions
        predicate = new DateTimeWithinRangePredicate(minTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_notWithinDateTimeRange_returnsFalse() {
        Appointment appointmentToTest = generateAppointmentWithDateTime(targetTime);
        LocalDateTime beforeBeforeTargetTime = beforeTargetTime.minusMinutes(10);
        LocalDateTime afterAfterTargetTime = beforeTargetTime.plusMinutes(10);

        // Before range
        DateTimeWithinRangePredicate predicate = new DateTimeWithinRangePredicate(afterTargetTime, afterAfterTargetTime);
        assertFalse(predicate.test(appointmentToTest));

        // After range
        predicate = new DateTimeWithinRangePredicate(beforeBeforeTargetTime, beforeTargetTime);
        assertFalse(predicate.test(appointmentToTest));

        // Before range, no endTime restriction
        predicate = new DateTimeWithinRangePredicate(afterTargetTime, maxTime);
        assertFalse(predicate.test(appointmentToTest));

        // After range, noStartTime restriction
        predicate = new DateTimeWithinRangePredicate(minTime, beforeTargetTime);
        assertFalse(predicate.test(appointmentToTest));
    }

    private Appointment generateAppointmentWithDateTime(LocalDateTime dateTime) {
        return new AppointmentBuilder().withDateTime(dateTime).build();
    }
}