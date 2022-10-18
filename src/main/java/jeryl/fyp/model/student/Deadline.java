package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task deadline added by the professor to a student's project.
 * Guarantees: immutable
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline names should only contain alphanumeric characters and spaces, and it should not be blank";

    public final DeadlineName fullDeadlineName;
    public final LocalDateTime fullDeadlineDateTime;

    /**
     * Constructs a {@code Deadline}.
     * @param deadlineName A valid deadline name.
     * @param deadlineDateTime A valid deadline date.
     */
    public Deadline(DeadlineName deadlineName, LocalDateTime deadlineDateTime) {
        requireNonNull(deadlineName);
        fullDeadlineName = deadlineName;
        fullDeadlineDateTime = deadlineDateTime;
    }
    /**
     * Returns the deadline task name.
     * @return The deadline name.
     */
    public DeadlineName getDeadlineName() {
        return fullDeadlineName;
    }

    /**
     * Returns the deadline datetime.
     * @return The deadline datetim.
     */
    public LocalDateTime getDeadlineDateTime() {
        return fullDeadlineDateTime;
    }
    /**
     * Returns true if both deadlines have the same name.
     * This defines a weaker notion of equality between two deadlines.
     * @param otherDeadline The deadline object compared to the current deadline.
     * @return True if both deadlines have the same name.
     */
    public boolean isSameDeadlineName(Deadline otherDeadline) {
        if (otherDeadline == this) {
            return true;
        }
        return otherDeadline != null
                && otherDeadline.getDeadlineName().equals(getDeadlineName());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDeadlineName())
                .append(", deadline: ")
                .append(getDeadlineDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) other;
        return otherDeadline.getDeadlineName().equals(getDeadlineName())
                && otherDeadline.getDeadlineDateTime().equals(getDeadlineDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullDeadlineName, fullDeadlineDateTime);
    }

    // Pending YH sorting compareTo(...)
    @Override
    public int compareTo(Deadline o) {
        return 0;
        /*
        if (this.age > o.age) {
            // if current object is greater,then return 1
            return 1;
        } else if (this.age < o.age) {

            // if other object is greater,then return -1
            return -1;
        } else {

            // if current object is equal to o,then return 0
            return 0;
        }
         */
    }
}
