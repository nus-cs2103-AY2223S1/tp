package seedu.address.model.person.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

/**
 * Represents a subject taken by the student
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
        "Subjects should only contain alphabets, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z]*$";

    public final String subjectName;

    private HashMap<String, Integer> attendance;

    private HashMap<String, Grade> grades;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject.
     */
    public Subject(String subjectName) {
        requireNonNull(subjectName);
        checkArgument(isValidSubject(subjectName), MESSAGE_CONSTRAINTS);

        this.subjectName = subjectName;
        this.attendance = new HashMap<>();
        this.grades = new HashMap<>();
    }

    /**
     * Returns true if a given string is a valid Subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof Subject // instanceof handles nulls
                   && subjectName.equals(((Subject) other).subjectName)); // state check
    }

    @Override
    public String toString() {
        return subjectName;
    }

}
