package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * TaskDescription class represents the description of the task.
 */
public class TaskDescription implements Comparable<TaskDescription> {
    public static final String DESCRIPTION_CONSTRAINTS =
            "The description of the task should not be empty";

    public final String description;

    /**
     * The constructor of the TaskDescription class. Sets the
     * description of the class.
     *
     * @param description The description of the class.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), DESCRIPTION_CONSTRAINTS);
        this.description = description;
    }

    public static boolean isValidDescription(String description) {
        return description.length() > 0;
    }

    @Override
    public boolean equals(Object otherDescription) {
        return otherDescription == this || (otherDescription instanceof TaskDescription
                && description.equalsIgnoreCase((((TaskDescription) otherDescription).description)));
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public int compareTo(TaskDescription otherDescription) {
        return this.description.compareTo(otherDescription.description);
    }
}


