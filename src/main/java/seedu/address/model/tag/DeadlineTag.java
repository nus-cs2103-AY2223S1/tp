package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Tag represents a tag which contains the deadline for the task
 */
public class DeadlineTag implements Comparable<DeadlineTag> {
    public static final String DEADLINE_TAG_FORMAT_CONSTRAINTS =
            "The deadline should be in the format DD-MM-YYYY. DD should be between "
                    + "1 and 31(both inclusive)\nand MM "
                    + "should be between 1 and 12(both inclusive)";
    public static final String DEADLINE_TAG_INVALID_DATE =
            "The deadline should be a valid date";
    public static final String DEADLINE_TAG_DATE_HAS_PASSED =
            "The deadline should not be earlier than today's date.";
    private static final String dateFormat = "^(?:0[1-9]|[1-2][0-9]|3[0-1])[-]"
            + "(?:0[1-9]|1[0-2])[-][0-9]{4}";
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
     * Checks that the format of the date is correct
     *
     * @param date The date string being checked
     * @return true if the format of string is correct; else return false;
     */
    public static boolean checkDateFormat(String date) {
        return date.matches(dateFormat);
    }

    /**
     * Checks whether the deadline given is valid.
     *
     * @param testDateAdded The date being checked for validity.
     * @return true if the date is valid; else return false.
     */
    public static boolean isValidDeadline(LocalDate testDateAdded) {
        if (testDateAdded == null) {
            return false;
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
