package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Appointment;
import seedu.address.testutil.AppointmentBuilder;

class CombinedAppointmentPredicateTest {

    private final String empty = "";
    private final LocalDateTime minTime = LocalDateTime.MIN;
    private final LocalDateTime maxTime = LocalDateTime.MAX;

    private final String reason = "Sleep Apnea";
    private final String differentReason = "Tinnitus";

    private final LocalDateTime targetTime = LocalDateTime.parse("2000-12-31T12:34");
    private final LocalDateTime beforeTargetTime = LocalDateTime.parse("1900-01-01T00:00");
    private final LocalDateTime afterTargetTime = LocalDateTime.parse("2100-01-01T00:00");

    // 4 different LocalDateTimes, with each being later than the last.
    private final LocalDateTime time0 = LocalDateTime.parse("2000-12-31T12:34");
    private final LocalDateTime time1 = LocalDateTime.parse("2001-12-31T12:34");
    private final LocalDateTime time2 = LocalDateTime.parse("2002-12-31T12:34");
    private final LocalDateTime time3 = LocalDateTime.parse("2003-12-31T12:34");
    private final LocalDateTime time4 = LocalDateTime.parse("2004-12-31T12:34");


    @Test
    public void equals() {
        CombinedAppointmentPredicate firstPredicate = new CombinedAppointmentPredicate(reason, time1, time2);
        CombinedAppointmentPredicate firstPredicateCopy = new CombinedAppointmentPredicate(reason, time1, time2);
        CombinedAppointmentPredicate differentPredicate =
                new CombinedAppointmentPredicate(differentReason, time3, time4);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, differentPredicate);

        // Different reason not equals
        CombinedAppointmentPredicate testPredicate = new CombinedAppointmentPredicate(differentReason, time1, time2);
        assertNotEquals(testPredicate, firstPredicate);

        // Different different startTime not equals
        testPredicate = new CombinedAppointmentPredicate(reason, time0, time2);
        assertNotEquals(testPredicate, firstPredicate);

        // Different endTime not equals
        testPredicate = new CombinedAppointmentPredicate(reason, time1, time3);
        assertNotEquals(testPredicate, firstPredicate);
    }

    @Test
    public void test_appointmentFufillsPredicate_returnsTrue() {
        Appointment appointmentToTest = new AppointmentBuilder().withReason(reason).withDateTime(targetTime).build();

        // Within search criteria
        CombinedAppointmentPredicate predicate =
                new CombinedAppointmentPredicate(reason, beforeTargetTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Exact search criteria
        predicate = new CombinedAppointmentPredicate(reason, targetTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Can find reason without full match
        predicate = new CombinedAppointmentPredicate("Sleep Ap", targetTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // No start time restriction
        predicate = new CombinedAppointmentPredicate(reason, minTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // No end time restriction
        predicate = new CombinedAppointmentPredicate(reason, targetTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));

        // No time restriction
        predicate = new CombinedAppointmentPredicate(reason, minTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));

        // Empty reason
        predicate = new CombinedAppointmentPredicate(empty, beforeTargetTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // No reason, no time restriction
        predicate = new CombinedAppointmentPredicate(empty, minTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_appointmentNotFufillPredicate_returnsFalse() {
        Appointment appointmentToTest = new AppointmentBuilder().withReason(reason).withDateTime(targetTime).build();
        LocalDateTime beforeBeforeTargetTime = beforeTargetTime.minusMinutes(10);
        LocalDateTime afterAfterTargetTime = beforeTargetTime.plusMinutes(10);

        // Outside search criteria
        CombinedAppointmentPredicate predicate =
                new CombinedAppointmentPredicate(differentReason, afterTargetTime, afterAfterTargetTime);
        assertFalse(predicate.test(appointmentToTest));

        // Different search criteria, no time restriction
        predicate = new CombinedAppointmentPredicate(differentReason, minTime, maxTime);
        assertFalse(predicate.test(appointmentToTest));

        // Same reason, outside time range
        predicate = new CombinedAppointmentPredicate(reason, beforeBeforeTargetTime, beforeTargetTime);
        assertFalse(predicate.test(appointmentToTest));

        // Incomplete reason match
        String stricterReason = reason + " and sore throat";
        predicate = new CombinedAppointmentPredicate(stricterReason, minTime, minTime);
        assertFalse(predicate.test(appointmentToTest));
    }
}