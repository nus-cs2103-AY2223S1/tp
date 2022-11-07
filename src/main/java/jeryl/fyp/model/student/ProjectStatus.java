package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's project status in the FYP manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidProjectStatus(String)}
 */
public class ProjectStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be of the form YTS, IP OR DONE";

    public final String projectStatus;

    /**
     * Constructs a {@code ProjectStatus}.
     * @param status A valid project status
     */
    public ProjectStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidProjectStatus(status), MESSAGE_CONSTRAINTS);
        projectStatus = status;
    }

    /**
     * Constructs a default {@code ProjectStatus}.
     */
    public ProjectStatus() {
        this("YTS");
    }

    public String toString() {
        return projectStatus;
    }

    /**
     * Returns true if a given status is one of our enumeration constants
     */
    public static boolean isValidProjectStatus(String test) {
        return (test.equals("YTS") || test.equals("IP") || test.equals("DONE"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectStatus // instanceof handles nulls
                && projectStatus.equals(((ProjectStatus) other).projectStatus)); // state check
    }

}
