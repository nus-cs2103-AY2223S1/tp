package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's name.
 */
public class Name {

    /**
     * Represents an empty Project name.
     */
    private static class EmptyName extends Name {
        private static final Name EMPTY_NAME = new EmptyName();

        private EmptyName() {
            super("");
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of project name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String projectName;

    /**
     * Constructs a project Name.
     *
     * @param name A valid project name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.projectName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return projectName;
    }
}
