package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Priority Tag represents a tag which contains the priority status of the task.
 */
public class PriorityTag {
    public static final String PRIORITY_TAG_CONSTRAINTS =
            "Priority status of tag should only be HIGH, MEDIUM or LOW";
    public static final String VALIDATION_REGEX = "high|low|medium";
    public final String status;

    /**
     * Constructor of the priority tag. Sets the priority
     * status to the tag.
     *
     * @param status The priority status of the tag.
     */
    public PriorityTag(String status) {
        requireNonNull(status);
        checkArgument(isValidTag(status), PRIORITY_TAG_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Checks whether the priority status in the tag is valid.
     *
     * @param testTag The tag that is being checked.
     * @return true if the priority status is valid; else return false.
     */
    public static boolean isValidTag(String testTag) {
        if (testTag == null) {
            return true;
        }
        String testTagStatus = testTag.toLowerCase();
        return testTagStatus.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object otherTag) {
        return otherTag == this || (otherTag instanceof PriorityTag
                && status.equalsIgnoreCase((((PriorityTag) otherTag).status)));
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
