package taskbook.model.task.enums;

import java.util.Arrays;

/**
 * Represents whether the Task has been assigned to the user,
 * or the user has assigned to someone else.
 */
public enum Assignment {
    FROM, TO;

    public static final String MESSAGE_CONSTRAINTS =
            "Assignment should be either FROM or TO";

    @Override
    public String toString() {
        switch (this) {
        case FROM:
            return "Assigned by";
        case TO:
            return "Assigned to";
        default:
            return " ";
        }
    }

    public static boolean isValidAssignment(String test) {
        return Arrays.stream(values()).map(Assignment::name).anyMatch(code -> code.equals(test));
    }
}
