package modtrekt.model.deadline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline in the task list.
 * Requires a due date to be provided.
 */
public class Deadline {
    private final String description;
    private final LocalDate dueDate;

    /**
     * Constructor for deadline object.
     *
     * @param description description of task
     * @param dateString string representing due date in YYYY-MM-DD format
     */
    public Deadline(String description, String dateString) {
        this.description = description;
        this.dueDate = getDateFromString(dateString);
    }

    private LocalDate getDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", description, dueDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));
    }
}
