package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyDateTime;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyReason;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

class CombinedAppointmentPredicateTest {

    private final LocalDateTime minTime = LocalDateTime.MIN;
    private final LocalDateTime maxTime = LocalDateTime.MAX;

    private final String reason = "Sleep Apnea";
    private final String differentReason = "Tinnitus";

    private final LocalDateTime targetTime = LocalDateTime.parse("2000-12-31T12:34");
    private final LocalDateTime beforeTargetTime = LocalDateTime.parse("1900-01-01T00:00");
    private final LocalDateTime afterTargetTime = LocalDateTime.parse("2100-01-01T00:00");

    private final LocalDateTime beforeBeforeTargetTime = beforeTargetTime.minusMinutes(10);
    private final LocalDateTime afterAfterTargetTime = afterTargetTime.plusMinutes(10);

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
        predicate = new CombinedAppointmentPredicate(EMPTY_STRING, beforeTargetTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // No reason, no time restriction
        predicate = new CombinedAppointmentPredicate(EMPTY_STRING, minTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_appointmentNotFufillPredicate_returnsFalse() {
        Appointment appointmentToTest = new AppointmentBuilder().withReason(reason).withDateTime(targetTime).build();

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
        predicate = new CombinedAppointmentPredicate(stricterReason, minTime, maxTime);
        assertFalse(predicate.test(appointmentToTest));
    }

    @Test
    public void test_personFufillsPredicateReasonOnly_returnsTrue() {
        String reasonToTest = "Sore Throat";
        Appointment appointmentToTest = new AppointmentBuilder().withReason(reasonToTest).build();

        // Same reason
        CombinedAppointmentPredicate predicate = generateCombinedAppointmentPredicateWithOnlyReason(reasonToTest);
        assertTrue(predicate.test(appointmentToTest));

        // Contains sequence, can find reason through spaces
        predicate = generateCombinedAppointmentPredicateWithOnlyReason("re Thro");
        assertTrue(predicate.test(appointmentToTest));

        // Mixed-case sequence
        predicate = generateCombinedAppointmentPredicateWithOnlyReason("OrE tHROAt");
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_personNotFufillPredicateReasonOnly_returnsFalse() {
        String reasonToTest = "Sore Throat";
        Appointment appointmentToTest = new AppointmentBuilder().withReason(reasonToTest).build();

        // Non-matching sequence
        CombinedAppointmentPredicate predicate = generateCombinedAppointmentPredicateWithOnlyReason("Cough");
        assertFalse(predicate.test(appointmentToTest));

        // Incomplete match
        predicate = generateCombinedAppointmentPredicateWithOnlyReason("Sore Throats");
        assertFalse(predicate.test(appointmentToTest));

        // Incomplete match
        predicate = generateCombinedAppointmentPredicateWithOnlyReason("Canker Sore");
        assertFalse(predicate.test(appointmentToTest));
    }

    @Test
    public void test_personFufillsPredicateDateTimeOnly_returnsTrue() {
        Appointment appointmentToTest = new AppointmentBuilder().withDateTime(targetTime).build();

        // Within range
        CombinedAppointmentPredicate predicate =
                generateCombinedAppointmentPredicateWithOnlyDateTime(beforeTargetTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Same dateTime with startDateTime
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(targetTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Same dateTime with endDateTime
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(beforeTargetTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Exact same time
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(targetTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no startTime restriction
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(minTime, afterTargetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no endTime restriction
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(beforeTargetTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no startTime restriction, same time as endTime
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(minTime, targetTime);
        assertTrue(predicate.test(appointmentToTest));

        // Within range, no endTime restriction, same time as startTime
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(targetTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));

        // No restrictions
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(minTime, maxTime);
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_personNotFufillPredicateDateTimeOnly_returnsFalse() {
        Appointment appointmentToTest = new AppointmentBuilder().withDateTime(targetTime).build();

        // Before range
        CombinedAppointmentPredicate predicate =
                generateCombinedAppointmentPredicateWithOnlyDateTime(afterTargetTime, afterAfterTargetTime);
        assertFalse(predicate.test(appointmentToTest));

        // After range
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(beforeBeforeTargetTime, beforeTargetTime);
        assertFalse(predicate.test(appointmentToTest));

        // Before range, no endTime restriction
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(afterTargetTime, maxTime);
        assertFalse(predicate.test(appointmentToTest));

        // After range, noStartTime restriction
        predicate = generateCombinedAppointmentPredicateWithOnlyDateTime(minTime, beforeTargetTime);
        assertFalse(predicate.test(appointmentToTest));
    }
}
