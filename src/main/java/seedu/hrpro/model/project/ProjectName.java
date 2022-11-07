package seedu.hrpro.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.util.AppUtil.checkArgument;

/**
 * Represent a project name in HR PRO Max++.
 */
public class ProjectName {
    public static final String MESSAGE_CONSTRAINTS =
            "Project names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the Project name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code ProjectName}.
     *
     * @param projectName A valid name.
     */
    public ProjectName(String projectName) {
        requireNonNull(projectName);
        checkArgument(isValidProjectName(projectName), MESSAGE_CONSTRAINTS);
        fullName = projectName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidProjectName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectName // instanceof handles nulls
                && fullName.equalsIgnoreCase(((ProjectName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
