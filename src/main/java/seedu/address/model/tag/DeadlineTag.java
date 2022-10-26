package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Tag represents a tag which contains the deadline for the task
 */
public class DeadlineTag implements Comparable<DeadlineTag> {
    public static final String DEADLINE_TAG_CONSTRAINTS =
            "Deadline of the tag should be in the format dd-mm-yyyy"
            + " and should be a valid date entry";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public final LocalDate deadline;

    /**
     * Constructor of the DeadlineTag. Sets the deadline of the
     * tag.
     *
     * @param deadline The deadline of the tag.
     */
    public DeadlineTag(LocalDate deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    /**
     * Checks whether the deadline given is valid.
     *
     * @param testDateAdded The date being checked for validity.
     * @return true if the date is valid; else return false.
     */
    public static boolean isValidDeadline(LocalDate testDateAdded) {
        if (testDateAdded == null) {
            return true;
        }
        return testDateAdded.compareTo(LocalDate.now()) >= 0;
    }

    @Override
    public boolean equals(Object otherDate) {
        return otherDate == this || (otherDate instanceof DeadlineTag
                && deadline.equals(((DeadlineTag) otherDate).deadline));
    }

    @Override
    public String toString() {
        return deadline.format(dtf);
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

    @Override
    public int compareTo(DeadlineTag otherDeadlineTag) {
        return this.deadline.compareTo(otherDeadlineTag.deadline);
    }
}
