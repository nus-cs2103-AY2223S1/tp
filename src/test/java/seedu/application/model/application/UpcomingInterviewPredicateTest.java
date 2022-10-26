package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        UpcomingInterviewPredicateStub predicate = new UpcomingInterviewPredicateStub();

        // interview is a minute later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-10-26").withInterviewTime("1801").build()).build()));

        // interview is an hour later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-10-26").withInterviewTime("1900").build()).build()));

        // interview is a day later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-10-27").withInterviewTime("1800").build()).build()));

        // interview is exactly a week later
        assertTrue(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-11-02").withInterviewTime("1800").build()).build()));
    }

    @Test
    public void test_isNotUpcomingInterview_returnsFalse() {
        UpcomingInterviewPredicateStub predicate = new UpcomingInterviewPredicateStub();

        // interview was a minute earlier
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-10-26").withInterviewTime("1759").build()).build()));

        // interview was a week earlier
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-10-19").withInterviewTime("1800").build()).build()));

        // interview is one week and one minute later
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-11-02").withInterviewTime("1801").build()).build()));

        // interview is one year later
        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2023-10-26").withInterviewTime("1800").build()).build()));
    }

    @Test
    public void test_interviewIsArchived_returnsFalse() {
        UpcomingInterviewPredicateStub predicate = new UpcomingInterviewPredicateStub();

        assertFalse(predicate.test(new ApplicationBuilder().withInterview(
                new InterviewBuilder().withInterviewDate("2022-10-26").withInterviewTime("1900").build())
                .withArchiveStatus(true).build()));
    }

    @Test
    public void test_interviewIsEmpty_returnsFalse() {
        UpcomingInterviewPredicateStub predicate = new UpcomingInterviewPredicateStub();

        assertFalse(predicate.test(new ApplicationBuilder().build()));
    }
}
