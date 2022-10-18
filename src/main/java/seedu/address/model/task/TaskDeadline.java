package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent a task deadLine in HR PRO Max++.
 */
public class TaskDeadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format YYYY-MM-DD and should not be blank";

    /*
     * The date should be in YYYY-MM-DD format.
     *
     */
    public static final String VALIDATION_REGEX =
            "^((2000|2400|2800|(2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

    public final String taskDeadLine;

    /**
     * Constructs an {@code TaskDeadline}.
     *
     * @param taskDeadLine A valid task deadLine.
     */
    public TaskDeadline(String taskDeadLine) {
        requireNonNull(taskDeadLine);
        checkArgument(isValidTaskDeadline(taskDeadLine), MESSAGE_CONSTRAINTS);
        this.taskDeadLine = taskDeadLine;
    }

    /**
     * Returns if a given string is a valid taskDeadLine.
     */
    public static boolean isValidTaskDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskDeadLine.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.task.TaskDeadline // instanceof handles nulls
                && taskDeadLine.equals(((seedu.address.model.task.TaskDeadline) other).taskDeadLine)); // state check
    }

    @Override
    public int hashCode() {
        return taskDeadLine.hashCode();
    }
}
