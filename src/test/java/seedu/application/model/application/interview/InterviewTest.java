package seedu.application.model.application.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INTERVIEW_TIME_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_LOCATION_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROUND_FACEBOOK;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_FACEBOOK;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.InterviewBuilder;

public class InterviewTest {

    @Test
    public void isOnSameTime() {
        // same object -> returns true
        assertTrue(INTERVIEW_GOOGLE.isOnSameTime(INTERVIEW_GOOGLE));

        // null -> returns false
        assertFalse(INTERVIEW_GOOGLE.isOnSameTime(null));

        // same interviewDate and interviewTime, all other attributes different -> returns true
        Interview editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withLocation(VALID_LOCATION_FACEBOOK)
                .withRound(VALID_ROUND_FACEBOOK).build();
        assertTrue(INTERVIEW_GOOGLE.isOnSameTime(editedGoogleInterview));

        // different interviewDate, all other attributes same -> returns false
        editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withInterviewDate(VALID_INTERVIEW_DATE_FACEBOOK)
                .build();
        assertFalse(INTERVIEW_GOOGLE.isOnSameTime(editedGoogleInterview));

        // different interviewTime, all other attributes same -> returns false
        editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withInterviewTime(VALID_INTERVIEW_TIME_FACEBOOK)
                .build();
        assertFalse(INTERVIEW_GOOGLE.isOnSameTime(editedGoogleInterview));

        // across different date but within one hour -> return true
        Interview editedFacebookInterview = new InterviewBuilder(INTERVIEW_FACEBOOK).withInterviewDate("2022-10-02")
                .withInterviewTime("0001").build();
        editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withInterviewDate("2022-10-01")
                .withInterviewTime("2359").build();
        assertTrue(editedFacebookInterview.isOnSameTime(editedGoogleInterview));

        // across different date but one hour apart -> return false
        editedFacebookInterview = new InterviewBuilder(INTERVIEW_FACEBOOK).withInterviewDate("2022-10-02")
                .withInterviewTime("0159").build();
        editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withInterviewDate("2022-10-01")
                .withInterviewTime("2359").build();
        assertFalse(editedFacebookInterview.isOnSameTime(editedGoogleInterview));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Interview googleCopy = new InterviewBuilder(INTERVIEW_GOOGLE).build();
        assertEquals(INTERVIEW_GOOGLE, googleCopy);

        // same object -> returns true
        assertEquals(INTERVIEW_GOOGLE, INTERVIEW_GOOGLE);

        // null -> returns false
        assertNotEquals(null, INTERVIEW_GOOGLE);

        // different type -> returns false
        assertNotEquals(5, INTERVIEW_GOOGLE);

        // different interview -> returns false
        assertNotEquals(INTERVIEW_GOOGLE, INTERVIEW_FACEBOOK);

        // different interviewDate -> returns false
        Interview editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE)
                .withInterviewDate(VALID_INTERVIEW_DATE_FACEBOOK).build();
        assertNotEquals(INTERVIEW_GOOGLE, editedGoogleInterview);

        // different round -> returns false
        editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withRound(VALID_ROUND_FACEBOOK).build();
        assertNotEquals(INTERVIEW_GOOGLE, editedGoogleInterview);

        // different location -> returns false
        editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withLocation(VALID_LOCATION_FACEBOOK).build();
        assertNotEquals(INTERVIEW_GOOGLE, editedGoogleInterview);

        // different interviewTime -> returns false
        editedGoogleInterview = new InterviewBuilder(INTERVIEW_GOOGLE).withInterviewTime(VALID_INTERVIEW_TIME_FACEBOOK)
                .build();
        assertNotEquals(INTERVIEW_GOOGLE, editedGoogleInterview);

    }
}
