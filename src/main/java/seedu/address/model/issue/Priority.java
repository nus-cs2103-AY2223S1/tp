package seedu.address.model.issue;

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

        // adapted from https://stackoverflow.com/questions/4936819/java-check-if-enum-contains-a-given-string
        for (Priority p : Priority.values()) {
            if (p.name().equals(priority)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Priority: " + super.toString();
    }
}
