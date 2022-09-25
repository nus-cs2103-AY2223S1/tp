package seedu.address.model.task.enums;

/**
 * Represents whether the Task has been assigned to the user,
 * or the user has assigned to someone else.
 */
public enum Assignment {
    ASSIGNOR, ASSIGNEE;

    @Override
    public String toString() {
        switch (this) {
            case ASSIGNEE:
                return "Assigned from";
            case ASSIGNOR:
                return "Assigned to";
            default:
                return " ";
        }
    }
}
