package seedu.application.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.application.model.application.interview.Interview;
import seedu.application.model.application.interview.InterviewDate;
import seedu.application.model.application.interview.InterviewTime;
import seedu.application.model.application.interview.Location;
import seedu.application.model.application.interview.Round;

/**
 * A utility class to help with building Interview objects.
 */
public class InterviewBuilder {

    public static final String DEFAULT_ROUND = "Technical interview 1";
    public static final String DEFAULT_INTERVIEW_DATE = "2023-08-23";
    public static final String DEFAULT_INTERVIEW_TIME = "1545";
    public static final String DEFAULT_LOCATION = "11, Kallang Way 27, #03-14, 116546";

    private Round round;
    private InterviewDate interviewDate;
    private InterviewTime interviewTime;
    private Location location;

    /**
     * Creates an {@code InterviewBuilder} with the default details.
     */
    public InterviewBuilder() {
        round = new Round(DEFAULT_ROUND);
        interviewDate = new InterviewDate(DEFAULT_INTERVIEW_DATE);
        interviewTime = new InterviewTime(DEFAULT_INTERVIEW_TIME);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the InterviewBuilder with the data of {@code interviewToCopy}.
     */
    public InterviewBuilder(Interview interviewToCopy) {
        round = interviewToCopy.getRound();
        interviewDate = interviewToCopy.getInterviewDate();
        interviewTime = interviewToCopy.getInterviewTime();
        location = interviewToCopy.getLocation();
    }

    /**
     * Sets the {@code Round} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withRound(String round) {
        this.round = new Round(round);
        return this;
    }

    /**
     * Sets the {@code InterviewDate} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withInterviewDate(String interviewDate) {
        this.interviewDate = new InterviewDate(interviewDate);
        return this;
    }

    /**
     * Sets the {@code InterviewDate} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withInterviewDate(LocalDate interviewDate) {
        this.interviewDate = new InterviewDate(InterviewDate.COMMAND_DATE_FORMATTER.format(interviewDate));
        return this;
    }

    /**
     * Sets the {@code InterviewTime} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withInterviewTime(String interviewTime) {
        this.interviewTime = new InterviewTime(interviewTime);
        return this;
    }

    /**
     * Sets the {@code InterviewTime} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withInterviewTime(LocalTime interviewTime) {
        this.interviewTime = new InterviewTime(InterviewTime.COMMAND_TIME_FORMATTER.format(interviewTime));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public Interview build() {
        return new Interview(round, interviewDate, interviewTime, location);
    }

}
