package seedu.application.testutil;

import static seedu.application.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INTERVIEW_TIME_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_LOCATION_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROUND_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INTERVIEW_TIME_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_LOCATION_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROUND_FACEBOOK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.application.model.application.interview.Interview;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 */
public class TypicalInterviews {

    public static final Interview INTERVIEW_SHOPEE = new InterviewBuilder().withRound("Manager interview")
            .withInterviewDate("2023-10-19").withInterviewTime("1145").withLocation("Zoom")
            .build();
    public static final Interview INTERVIEW_BYTEDANCE = new InterviewBuilder().withRound("Online assessment")
            .withInterviewDate("2023-09-08").withInterviewTime("1400").withLocation("Bytedance HQ").build();

    // Manually added
    public static final Interview INTERVIEW_JANE_STREET = new InterviewBuilder().withRound("Technical interview 2")
            .withInterviewDate("2024-08-14").withInterviewTime("1300").withLocation("Skype").build();

    // Manually added - Interview's details found in {@code CommandTestUtil}
    public static final Interview INTERVIEW_GOOGLE = new InterviewBuilder().withRound(VALID_ROUND_GOOGLE)
            .withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE)
            .withInterviewTime(VALID_INTERVIEW_TIME_GOOGLE).withLocation(VALID_LOCATION_GOOGLE).build();
    
    public static final Interview INTERVIEW_FACEBOOK = new InterviewBuilder().withRound(VALID_ROUND_FACEBOOK)
            .withInterviewDate(VALID_INTERVIEW_DATE_FACEBOOK)
            .withInterviewTime(VALID_INTERVIEW_TIME_FACEBOOK).withLocation(VALID_LOCATION_FACEBOOK).build();

    public static final String KEYWORD_MATCHING_GOOGLE = "Google"; // A keyword that matches GOOGLE

    private TypicalInterviews() {} // prevents instantiation

    /*
    /**
     * Returns an {@code ApplicationBook} with all the typical interviews.

    public static ApplicationBook getTypicalInterviewBook() {
        ApplicationBook ab = new ApplicationBook();
        for (Interview interview : getTypicalInterviews()) {
            ab.addInterview(interview);
        }
        return ab;
    }
    
     */



    public static List<Interview> getTypicalInterviews() {
        return new ArrayList<>(Arrays.asList(INTERVIEW_SHOPEE, INTERVIEW_BYTEDANCE, INTERVIEW_JANE_STREET));
    }
}
