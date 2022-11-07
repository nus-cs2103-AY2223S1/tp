package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

/**
 * Represents a Student's tutorial group in the address book.
 */
public class TutorialGroup {
    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial group should follow the format Txx, where x is a numeric value, and it should not be blank";


    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[T][0-9]{2}";

    public static final String DEFAULT_TUTORIAL_GROUP = "";

    private final String tutorialGroup;

    private final ArrayList<Student> students = new ArrayList<>();


    /**
     * Constructs a {@code TutorialGroup}.
     *
     * @param group A valid tutorial group.
     */
    public TutorialGroup(String group) {
        requireNonNull(group);
        checkArgument(isValidTutorialGroup(group), MESSAGE_CONSTRAINTS);
        tutorialGroup = group;
    }


    /**
     * Returns true if a given string is a valid tutorial group.
     */
    public static boolean isValidTutorialGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void addStudentToTutorialGroup(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Returns true if a given tutorial group is the same.
     * @param group the given tutorial group.
     * @return true if the two tutorial groups are the same, false otherwise.
     */
    public boolean isSameTutorialGroup(TutorialGroup group) {
        if (this.tutorialGroup == null && group.tutorialGroup == null) {
            return true;
        } else if (this.tutorialGroup == null) {
            return false;
        }
        return this.tutorialGroup.equals(group.tutorialGroup);
    }

    @Override
    public String toString() {
        return tutorialGroup;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroup // instanceof handles nulls
                && this.isSameTutorialGroup(((TutorialGroup) other))); // state check
    }

    @Override
    public int hashCode() {
        return tutorialGroup.hashCode();
    }

}
