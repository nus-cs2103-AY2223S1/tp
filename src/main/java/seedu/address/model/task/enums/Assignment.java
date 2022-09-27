package seedu.address.model.task.enums;

/**
 * Represents whether the Task has been assigned to the user,
 * or the user has assigned to someone else.
 */
public enum Assignment {
    FROM, TO;

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
}
