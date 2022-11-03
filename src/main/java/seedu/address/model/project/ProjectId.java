package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Project's id.
 */
public class ProjectId {

    public static final String MESSAGE_CONSTRAINTS = "Project ID must be a positive integer, less than " +
            Integer.MAX_VALUE + ".";
    public static final String MESSAGE_INVALID = "Project ID must be an integer. "
            + "No existing project with this project ID";
    private int projectId;

    /**
     * Construct's an project's id.
     *
     * @param id A valid project id.
     */
    public ProjectId(int id) {
        requireNonNull(id);
        this.projectId = id;
    }


    /**
     * Checks if this ProjectID is empty.
     * @return false since the ProjectID is not empty.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Represents an Empty ProjectID.
     */
    public static class EmptyProjectId extends ProjectId {
        public static final ProjectId EMPTY_PROJECT_ID = new EmptyProjectId();
        public EmptyProjectId() {
            super(Integer.MAX_VALUE);
        }


        /**
         * Checks if this ProjectID is empty.
         * @return true since the ProjectID is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "";
        }
    }

    /**
     * Checks whether the project ID string is valid.
     * @param projectId
     * @return Boolean value representing the validity of the project ID string.
     */
    public static boolean isValidProjectId(String projectId) {
        try {
            Integer pid = Integer.parseInt(projectId);
            return pid > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getIdInt() {
        return this.projectId;
    }

    public String uiRepresentation() {
        return "(#" + this + ")";
    }

    @Override
    public String toString() {
        return String.valueOf(this.projectId);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectId // instanceof handles nulls
                && projectId == (((ProjectId) other).projectId)); // state check
    }
}

