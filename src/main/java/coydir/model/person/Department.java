package coydir.model.person;

import static coydir.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's department in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidDepartment(String)}
 */
public class Department {

    public static final String MESSAGE_CONSTRAINTS =
            "There are a select number of valid Departments. "
            + "Input (case-insensitive) is invalid if it is not in this list: \n"
            + Department.listValidDepartments();

    /*
     * The first character of the department must not be a whitespace.
     * A second step is required, for checking against the list of valid departments.
     * This second step is not conducted using the regex string.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final String[] VALID_DEPARTMENTS = {
        "Administration",
        "Board of Directors",
        "Customer Service",
        "Finance",
        "General Management",
        "Human Resources ",
        "Information Technology",
        "Legal",
        "Marketing",
        "Operations",
        "Product Management",
        "Production",
        "Quality Assurance",
        "Research and Development",
        "Sales",
        "Technology"
        };

    public final String value;

    /**
     * Constructs a {@code Department}.
     *
     * @param department A valid department.
     */
    public Department(String department) {
        requireNonNull(department);
        checkArgument(isValidDepartment(department), MESSAGE_CONSTRAINTS);
        this.value = Department.findValidDepartment(department);
    }

    /**
     * Returns true if a given string is a valid department.
     */
    public static boolean isValidDepartment(String test) {
        boolean hasNoWhitespacePrefix = test.matches(VALIDATION_REGEX);
        if (!hasNoWhitespacePrefix) {
            return false;
        }

        String department = findValidDepartment(test);
        if (department == null) {
            return false;
        } else {
            return true;
        }
    }

    private static String findValidDepartment(String test) {
        String lowerCaseTest = test.toLowerCase();
        for (String department : VALID_DEPARTMENTS) {
            if (lowerCaseTest == department.toLowerCase()) {
                return department;
            }
        }

        return null;
    }

    private static String listValidDepartments() {
        String allValidDepartments = "";
        for (String department : VALID_DEPARTMENTS) {
            String toAdd = String.format("'%s', ", department);
            allValidDepartments += toAdd;
        }
        return allValidDepartments.substring(0, allValidDepartments.length() - 2);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Department // instanceof handles nulls
                && this.value.equals(((Department) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

}
