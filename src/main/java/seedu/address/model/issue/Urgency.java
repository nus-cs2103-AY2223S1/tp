package seedu.address.model.issue;

import java.util.ArrayList;

/**
 * Represents the various urgency levels of an issue.
 */
public enum Urgency {
    NONE, LOW, MEDIUM, HIGH;

    public static final String MESSAGE_CONSTRAINTS = "Urgency should be an integer 0 (NONE), 1 (LOW), 2 (MEDIUM) or "
            + "3 (HIGH)";
    public static final String MESSAGE_STRING_CONSTRAINTS = "Urgency should be High, Low, Medium or "
            + "None.";

    /**
     * Checks if the urgency integer string is valid.
     * @param urgencyValue
     * @return Boolean denoting whether the urgency integer string is valid.
     */
    public static boolean isValidUrgency(String urgencyValue) {

        ArrayList<Integer> priorities = new ArrayList<Integer>();
        priorities.add(0);
        priorities.add(1);
        priorities.add(2);
        priorities.add(3);
        for (Integer i: priorities) {
            if (Integer.valueOf(i).equals(Integer.valueOf(urgencyValue))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if urgency string is valid.
     * @param urgency
     * @return Boolean denoting whether the urgency integer string is valid.
     */
    public static boolean isValidUrgencyString(String urgency) {
        try {
            Urgency tempUrgency = valueOf(urgency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String uiRepresentation() {
        return "Urgency: " + super.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
