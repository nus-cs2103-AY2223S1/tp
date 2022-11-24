package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NOSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_THROAT;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyDateTime;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyReason;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyTags;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Appointment;
import seedu.address.testutil.AppointmentBuilder;

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

    private final String tag1 = VALID_TAG_EAR;
    private final String tag2 = VALID_TAG_NOSE;
    private final String tag3 = VALID_TAG_THROAT;
    private final List<String> emptyList = Collections.emptyList();
    private final List<String> listWithTag1And2 = List.of(tag1, tag2);
    private final List<String> listWithAllTags = List.of(tag1, tag2, tag3);


    @Test
    public void equals() {
        CombinedAppointmentPredicate firstPredicate = new CombinedAppointmentPredicate(reason, time1, time2, emptyList);
        CombinedAppointmentPredicate firstPredicateCopy =
                new CombinedAppointmentPredicate(reason, time1, time2, emptyList);
        CombinedAppointmentPredicate differentPredicate =
                new CombinedAppointmentPredicate(differentReason, time3, time4, listWithTag1And2);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, differentPredicate);

        // Different reason not equals
        CombinedAppointmentPredicate testPredicate =
                new CombinedAppointmentPredicate(differentReason, time1, time2, emptyList);
        assertNotEquals(testPredicate, firstPredicate);

        // Different different startTime not equals
        testPredicate = new CombinedAppointmentPredicate(reason, time0, time2, emptyList);
        assertNotEquals(testPredicate, firstPredicate);

        // Different endTime not equals
        testPredicate = new CombinedAppointmentPredicate(reason, time1, time3, emptyList);
        assertNotEquals(testPredicate, firstPredicate);

        // Different list not equals
        testPredicate = new CombinedAppointmentPredicate(reason, time1, time3, listWithTag1And2);
        assertNotEquals(testPredicate, firstPredicate);
    }

    @Test
    public void test_appointmentFufillsPredicate_returnsTrue() {
        Appointment appointmentToTest =
                new AppointmentBuilder().withReason(reason).withDateTime(targetTime).withTags(tag1, tag2).build();

        // Within search criteria
        CombinedAppointmentPredicate predicate =
                new CombinedAppointmentPredicate(reason, beforeTargetTime, afterTargetTime, emptyList);
        assertTrue(predicate.test(appointmentToTest));

        // Exact search criteria
        predicate = new CombinedAppointmentPredicate(reason, targetTime, targetTime, listWithTag1And2);
        assertTrue(predicate.test(appointmentToTest));

        // Can find reason without full match
        predicate = new CombinedAppointmentPredicate("Sleep Ap", targetTime, targetTime, listWithTag1And2);
        assertTrue(predicate.test(appointmentToTest));

        // No start time restriction
        predicate = new CombinedAppointmentPredicate(reason, minTime, targetTime, listWithTag1And2);
        assertTrue(predicate.test(appointmentToTest));

        // No end time restriction
        predicate = new CombinedAppointmentPredicate(reason, targetTime, maxTime, listWithTag1And2);
        assertTrue(predicate.test(appointmentToTest));

        // No time restriction
        predicate = new CombinedAppointmentPredicate(reason, minTime, maxTime, listWithTag1And2);
        assertTrue(predicate.test(appointmentToTest));

        // Empty reason
        predicate = new CombinedAppointmentPredicate(EMPTY_STRING, beforeTargetTime, afterTargetTime, listWithTag1And2);
        assertTrue(predicate.test(appointmentToTest));

        // No reason, No time restriction, No tag restriction
        predicate = new CombinedAppointmentPredicate(EMPTY_STRING, minTime, maxTime, emptyList);
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_appointmentNotFufillPredicate_returnsFalse() {
        Appointment appointmentToTest =
                new AppointmentBuilder().withReason(reason).withDateTime(targetTime).withTags(tag1).build();

        // Outside search criteria
        CombinedAppointmentPredicate predicate =
                new CombinedAppointmentPredicate(differentReason, afterTargetTime, afterAfterTargetTime, emptyList);
        assertFalse(predicate.test(appointmentToTest));

        // Different search criteria, no time restriction
        predicate = new CombinedAppointmentPredicate(differentReason, minTime, maxTime, emptyList);
        assertFalse(predicate.test(appointmentToTest));

        // Same reason, outside time range
        predicate = new CombinedAppointmentPredicate(reason, beforeBeforeTargetTime, beforeTargetTime, emptyList);
        assertFalse(predicate.test(appointmentToTest));

        // Incomplete reason match
        String stricterReason = reason + " and sore throat";
        predicate = new CombinedAppointmentPredicate(stricterReason, minTime, maxTime, emptyList);
        assertFalse(predicate.test(appointmentToTest));

        // Doesn't contain tag
        predicate = new CombinedAppointmentPredicate(reason, minTime, maxTime, listWithTag1And2);
        assertFalse(predicate.test(appointmentToTest));
    }

    @Test
    public void test_appointmentFufillsPredicateReasonOnly_returnsTrue() {
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
    public void test_appointmentNotFufillPredicateReasonOnly_returnsFalse() {
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
    public void test_appointmentFufillsPredicateDateTimeOnly_returnsTrue() {
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
    public void test_appointmentNotFufillPredicateDateTimeOnly_returnsFalse() {
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

    @Test
    public void test_appointmentFufillsPredicateTagOnly_returnsTrue() {
        Appointment appointmentToTest = new AppointmentBuilder().withDateTime(targetTime).withTags(tag1, tag2).build();

        // Contains all tags
        CombinedAppointmentPredicate predicate = generateCombinedAppointmentPredicateWithOnlyTags(tag1, tag2);
        assertTrue(predicate.test(appointmentToTest));

        // Contains only 1 tag
        predicate = generateCombinedAppointmentPredicateWithOnlyTags(tag2);
        assertTrue(predicate.test(appointmentToTest));

        // Contains tag, mixed-case works
        predicate = generateCombinedAppointmentPredicateWithOnlyTags("EaR");
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_appointmentNotFufillsPredicateTagOnly_returnsFalse() {
        Appointment appointmentToTest = new AppointmentBuilder().withDateTime(targetTime).withTags(tag1, tag2).build();

        // Non-matching tag
        CombinedAppointmentPredicate predicate = generateCombinedAppointmentPredicateWithOnlyTags(tag3);
        assertFalse(predicate.test(appointmentToTest));

        // Some tags not match
        predicate = generateCombinedAppointmentPredicateWithOnlyTags(tag1, tag2, tag3);
        assertFalse(predicate.test(appointmentToTest));
    }
}
