package seedu.address.model.application.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
    private final Remark remark;


    /**
     * Every field must be present and not null.
     */
    public Interview(Round round, InterviewDate interviewDate, InterviewTime interviewTime, Remark remark) {
        requireAllNonNull(round, interviewDate, interviewTime, remark);
        this.round = round;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.remark = remark;
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

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both applications have the same company and position.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isOnSameTime(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.getInterviewDate().equals(getInterviewDate())
                && otherInterview.getInterviewTime().equals(getInterviewTime());
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
                && otherApplication.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(round, interviewDate, interviewTime, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Interview: ")
                .append(getRound())
                .append("; Date: ")
                .append(getInterviewDate())
                .append("; Time: ")
                .append(getInterviewTime())
                .append("; Remark: ")
                .append(getRemark());

        return builder.toString();
    }

}
