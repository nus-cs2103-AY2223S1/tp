package seedu.address.model.issue;

import java.util.ArrayList;

/**
 * Represents the various priority levels of an issue.
 */
public enum Priority {
    NONE, LOW, MEDIUM, HIGH;

    public static final String MESSAGE_CONSTRAINTS = "PRIORITY NOT IMPLEMENTED";

    /**
     * Checks if the priority string is valid.
     * @param priority
     * @return Boolean denoting whether the priority string is valid.
     */
    public static boolean isValidPriority(String priority) {

        ArrayList<Integer> priorities = new ArrayList<Integer>();
        priorities.add(0);
        priorities.add(1);
        priorities.add(2);
        for (Integer i: priorities) {
            if (Integer.valueOf(i).equals(Integer.valueOf(priority))) {
                return true;
            }
        }
        return false;
    }

    public String uiRepresentation() {
        return "Priority: " + super.toString();
    }

    @Override
    public String toString() {
        return "Priority: " + super.toString();
    }
}
