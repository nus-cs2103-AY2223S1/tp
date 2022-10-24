package seedu.address.model.issue;

import java.util.ArrayList;

/**
 * Represents the various priority levels of an issue.
 */
public enum Priority {
    NONE, LOW, MEDIUM, HIGH;

    public static final String MESSAGE_CONSTRAINTS = "Priority should be an integer 0, 1 or 2";
    public static final String MESSAGE_STRING_REPRESENTATION_CONSTRAINTS = "Priority should be High, Low, Medium or " +
            "None.";

    /**
     * Checks if the priority integer string is valid.
     * @param priorityValue
     * @return Boolean denoting whether the priority integer string is valid.
     */
    public static boolean isValidPriority(String priorityValue) {

        ArrayList<Integer> priorities = new ArrayList<Integer>();
        priorities.add(0);
        priorities.add(1);
        priorities.add(2);
        for (Integer i: priorities) {
            if (Integer.valueOf(i).equals(Integer.valueOf(priorityValue))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if priority string is valid.
     * @param priority
     * @return Boolean denoting whether the priority integer string is valid.
     */
    public static boolean isValidPriorityString(String priority) {
        try {
            Priority tempPriority = valueOf(priority);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String uiRepresentation() {
        return "Priority: " + super.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
