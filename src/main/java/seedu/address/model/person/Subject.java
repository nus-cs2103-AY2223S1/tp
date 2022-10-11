package seedu.address.model.person;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
            "Subjects should only contain alphabets, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z]*$";

    public final String subjectName;

    public ArrayList<String> allSubjectsTaken;

    public Grade grades;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        allSubjectsTaken = new ArrayList<>();
        allSubjectsTaken.add(subject);
        subjectName = subject;
    }

    /**
     * Returns true if a given string is a valid Subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void add(String subject) {
        if (!allSubjectsTaken.contains(subject)) {
            allSubjectsTaken.add(subject);
        }
    }

    @Override
    public String toString() {
        return "Subject: " + allSubjectsTaken.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && subjectName.equals(((Subject) other).subjectName)); // state check
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

}
