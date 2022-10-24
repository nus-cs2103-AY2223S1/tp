package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project in the task panel.
 * Guarantees: immutable; name is valid as declared in {@link #isValidProjectName(String)}
 */
public class Project {

    public static final Project UNSPECIFIED = new Project();

    public static final String UNSPECIFIED_PROJECT_IDENTIFIER = "";

    public static final String MESSAGE_CONSTRAINTS = "Project names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String projectName;

    /**
     * Constructs a {@code Project}.
     *
     * @param projectName A valid project name.
     */
    public Project(String projectName) {
        requireNonNull(projectName);
        checkArgument(isValidProjectName(projectName), MESSAGE_CONSTRAINTS);
        this.projectName = projectName;
    }

    /**
     * Constructs an empty {@code Project}.
     */
    private Project() {
        this.projectName = "";
    }

    /**
     * Returns true if a given string is a valid project name.
     */
    public static boolean isValidProjectName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a project is unspecified.
     */
    public boolean isUnspecified() {
        return this == Project.UNSPECIFIED;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.project.Project // instanceof handles nulls
                && projectName.equals(((seedu.address.model.project.Project) other).projectName)); // state check
    }

    @Override
    public int hashCode() {
        return projectName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        if (isUnspecified()) {
            return UNSPECIFIED_PROJECT_IDENTIFIER;
        }
        return projectName;
    }

}
