package seedu.application.model.application.interview;

import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents an Application in the Application book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {

    // Identity fields
    private final Round round;
    private final InterviewDate interviewDate;
    private final InterviewTime interviewTime;
    private final Location location;
    private static final long DURATION = 1;


    /**
     * Every field must be present and not null.
     */
    public Interview(Round round, InterviewDate interviewDate, InterviewTime interviewTime, Location location) {
        requireAllNonNull(round, interviewDate, interviewTime, location);
        this.round = round;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.location = location;
    }

    public static LocalDateTime getInterviewDateTime(Interview interview) {
        return LocalDateTime.of(interview.getInterviewDate().value, interview.getInterviewTime().value);
    }

    public Round getRound() {
        return round;
    }

    public InterviewDate getInterviewDate() {
        return interviewDate;
    }

    public InterviewTime getInterviewTime() {
        return interviewTime;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns true if interview with one hour duration overlaps with one another.
     * This supports the checking of interviews across different date.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isOnSameTime(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }
        if (otherInterview == null) {
            return false;
        }

        LocalDateTime otherInterviewDateTime = getInterviewDateTime(otherInterview);
        LocalDateTime thisInterviewDateTime = getInterviewDateTime(this);
        return otherInterviewDateTime.equals(thisInterviewDateTime) ||
                ((otherInterviewDateTime.isAfter(thisInterviewDateTime)
                && otherInterviewDateTime.isBefore(thisInterviewDateTime.plusHours(DURATION)))
                || (thisInterviewDateTime.isAfter(otherInterviewDateTime)
                && thisInterviewDateTime.isBefore(otherInterviewDateTime.plusHours(DURATION))));
    }

    /**
     * Returns true if both applications have the same identity and data fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherApplication = (Interview) other;
        return otherApplication.getRound().equals(getRound())
                && otherApplication.getInterviewDate().equals(getInterviewDate())
                && otherApplication.getInterviewTime().equals(getInterviewTime())
                && otherApplication.getLocation().equals(getLocation());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(round, interviewDate, interviewTime, location);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getRound())
                .append("; Date: ")
                .append(getInterviewDate())
                .append("; Time: ")
                .append(getInterviewTime())
                .append("; Location: ")
                .append(getLocation());

        return builder.toString();
    }

}
