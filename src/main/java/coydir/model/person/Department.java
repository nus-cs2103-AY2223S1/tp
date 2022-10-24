package coydir.model.person;

import static coydir.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's department in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidDepartment(String)}
 */
public class Department {

    public static final String[] VALID_DEPARTMENTS = {
        "Administration",
        "Board of Directors",
        "Customer Service",
        "Finance",
        "General Management",
        "Human Resources",
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

    public static final String MESSAGE_CONSTRAINTS =
            "Input for department (case-insensitive) is invalid if it is not in this list: \n"
            + Department.listValidDepartments();

    private static final String NULL_DEPARTMENT = "N/A";

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
        String department = findValidDepartment(test);
        if (department.equals(NULL_DEPARTMENT)) {
            return false;
        } else {
            return true;
        }
    }

    private static String findValidDepartment(String test) {
        String lowerCaseTest = test.toLowerCase();
        for (String department : VALID_DEPARTMENTS) {
            if (lowerCaseTest.equals(department.toLowerCase())) {
                return department;
            }
        }

        return NULL_DEPARTMENT;
    }

    private static String listValidDepartments() {
        StringBuilder allValidDepartments = new StringBuilder();
        for (String department : VALID_DEPARTMENTS) {
            String toAdd = String.format("'%s', ", department);
            allValidDepartments.append(toAdd);
        }
        return allValidDepartments.substring(0, allValidDepartments.length() - 2);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Department // instanceof handles nulls
                && value.equals(((Department) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
