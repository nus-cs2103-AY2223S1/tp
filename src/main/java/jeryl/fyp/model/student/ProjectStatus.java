package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.AppUtil.checkArgument;

public class ProjectStatus {

    public final String projectStatus;

    //enum constants
    enum Status {
        YTS,
        IP,
        DONE;
    }


    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be of the form YTS, IP OR DONE";


    public ProjectStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidProjectStatus(status), MESSAGE_CONSTRAINTS);
        projectStatus = status;
    }

    public String toString() {
        return projectStatus;
    }

    /**
     * Returns true if a given status is one of our enumeration constants
     */
    public static boolean isValidProjectStatus(String test) {
        if (test.equals("YTS") || test.equals("IP") || test.equals("DONE")) {
            return true;
        } else {
            return false;
        }
    }
}
