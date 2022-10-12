package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Project's id.
 */
public class ProjectId {

    public static final String MESSAGE_CONSTRAINTS = "Project ID must exist in Project list";
    public static final String MESSAGE_INVALID = "Project ID must be an integer";
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

    public int getIdInt() {
        return this.projectId;
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
            super(-1);
        }


        /**
         * Checks if this ProjectID is empty.
         * @return true since the ProjectID is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }
    }

    /**
     * Checks whether the project ID string is valid.
     * @param projectId
     * @return Boolean value representing the validity of the project ID string.
     */
    public static boolean isValidProjectId(String projectId) {
        try {
            Integer.parseInt(projectId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String uiRepresentation() {
        return "(#" + toString() + ")";
    }
    @Override
    public String toString() {
        return String.valueOf(this.projectId);
    }

}

