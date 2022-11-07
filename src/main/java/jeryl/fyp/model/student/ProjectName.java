package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's project name in the FYP manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidProjectName(String)}
 */
public class ProjectName {

    // TODO -- Russell
    public static final String MESSAGE_CONSTRAINTS =
            "Project names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // TODO -- Russell
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullProjectName;

    /**
     * Constructs a {@code ProjectName}.
     *
     * @param projectName A valid project name.
     */
    public ProjectName(String projectName) {
        requireNonNull(projectName);
        checkArgument(isValidProjectName(projectName), MESSAGE_CONSTRAINTS);
        fullProjectName = projectName;
    }

    /**
     * Returns true if a given string is a valid project name.
     */
    public static boolean isValidProjectName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullProjectName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectName // instanceof handles nulls
                && fullProjectName.equals(((ProjectName) other).fullProjectName)); // state check
    }

    @Override
    public int hashCode() {
        return fullProjectName.hashCode();
    }

}
