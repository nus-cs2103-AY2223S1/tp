package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Project's id.
 */
public class ProjectId {

    public static final String MESSAGE_CONSTRAINTS = "Project ID must be a valid integer";
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

    public int getIdInt() {
        return this.projectId;
    }

    public String uiRepresentation() {
        return "(#" + toString() + ")";
    }
    @Override
    public String toString() {
        return String.valueOf(this.projectId);
    }

}

