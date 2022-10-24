package seedu.application.model.application.interview;

import org.junit.jupiter.api.Test;
import seedu.application.model.application.interview.Interview;
import seedu.application.testutil.InterviewBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_GOOGLE;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_FACEBOOK;

public class InterviewTest {

    /*
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Interview interview = new InterviewBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> interview.getTags().remove(0));
    }
    
     */

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
