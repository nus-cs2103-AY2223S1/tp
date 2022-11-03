package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.ApplicationBuilder;
import seedu.application.testutil.InterviewBuilder;

public class UpcomingInterviewPredicateTest {

    @Test
    public void equals() {
        UpcomingInterviewPredicate firstPredicate =
                new UpcomingInterviewPredicate();
        UpcomingInterviewPredicate secondPredicate =
                new UpcomingInterviewPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different object, same type -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isUpcomingInterview_returnsTrue() {
        UpcomingInterviewPredicate predicate = new UpcomingInterviewPredicate();

        // interviews at the present date and time are not considered upcoming as
        // they are considered to have passed

        // EP: interview is a minute later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now())
                        .withInterviewTime(LocalTime.now().plusMinutes(1)).build()).build()));

        // EP: interview is an hour later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now())
                        .withInterviewTime(LocalTime.now().plusHours(1)).build()).build()));

        // EP: interview is a day later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now().plusDays(1))
                        .withInterviewTime(LocalTime.now()).build()).build()));

        // EP: interview is exactly a week later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now().plusDays(7))
                        .withInterviewTime(LocalTime.now()).build()).build()));
    }

    @Test
    public void test_isNotUpcomingInterview_returnsFalse() {
        UpcomingInterviewPredicate predicate = new UpcomingInterviewPredicate();

        // EP: interview was a minute earlier
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now())
                        .withInterviewTime(LocalTime.now().minusMinutes(1)).build()).build()));

        // EP: interview was a week earlier
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now().minusDays(7))
                        .withInterviewTime(LocalTime.now()).build()).build()));

        // EP: interview is one week and one minute later
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now().plusDays(7))
                        .withInterviewTime(LocalTime.now().plusMinutes(1)).build()).build()));

        // EP: interview is one year later
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now().plusYears(1))
                        .withInterviewTime(LocalTime.now()).build()).build()));
    }

    @Test
    public void test_interviewIsArchived_returnsFalse() {
        UpcomingInterviewPredicate predicate = new UpcomingInterviewPredicate();

        // Heuristic: No more than one invalid input in a test case
        // interview is present and upcoming, but is archived
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate(LocalDate.now().plusDays(1))
                        .withInterviewTime(LocalTime.now()).build())
                .withArchiveStatus(true).build()));
    }

    @Test
    public void test_interviewIsEmpty_returnsFalse() {
        UpcomingInterviewPredicate predicate = new UpcomingInterviewPredicate();

        assertFalse(predicate.test(new ApplicationBuilder().build()));
    }
}
