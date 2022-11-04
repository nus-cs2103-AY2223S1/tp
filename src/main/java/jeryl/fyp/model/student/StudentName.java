package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's student name in the FYP manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentName(String)}
 */
public class StudentName {

    public static final String MESSAGE_CONSTRAINTS =
            "Student names should only contain alphabetic characters, spaces and slash(for Indian names),"
                    + " and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z ]*[a-zA-Z]*\\/*[a-zA-Z ]*[a-zA-Z]+";

    public final String fullStudentName;

    /**
     * Constructs a {@code StudentName}.
     *
     * @param name A valid name.
     */
    public StudentName(String name) {
        requireNonNull(name);
        checkArgument(isValidStudentName(name), MESSAGE_CONSTRAINTS);
        fullStudentName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStudentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullStudentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentName // instanceof handles nulls
                && fullStudentName.equals(((StudentName) other).fullStudentName)); // state check
    }

    @Override
    public int hashCode() {
        return fullStudentName.hashCode();
    }

}
