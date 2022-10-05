package seedu.address.model.student;

//import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.util.AppUtil.checkArgument;

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

    public static final String DEFAULT_TUTORIAL_GROUP = "T01";

    public final String tutorialGroup;

    private ArrayList<Student> students = new ArrayList<>();



    /**
     * Constructs a {@code TutorialGroup}.
     *
     * @param group A valid tutorial group.
     */
    public TutorialGroup(String group) {
        // requireNonNull(group);
        // checkArgument(isValidTutorialGroup(group), MESSAGE_CONSTRAINTS);
        // implement later
        tutorialGroup = group;
    }

    /**
     * Constructs a defualt {@code TutorialGroup}.
     */
    public TutorialGroup() {
        // requireNonNull(group);
        // checkArgument(isValidTutorialGroup(group), MESSAGE_CONSTRAINTS);
        // implement later
        tutorialGroup = DEFAULT_TUTORIAL_GROUP;
    }

    /**
     * Returns true if a given string is a valid tutorial group.
     */
    public static boolean isValidTutorialGroup(String test) {
        System.out.println(test);
        return test.matches(VALIDATION_REGEX);
    }

    public void addStudentToTutorialGroup(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean isSameTutorialGroup(TutorialGroup group) {
        return this.tutorialGroup.equals(group.tutorialGroup);
    }

    @Override
    public String toString() {
        return tutorialGroup;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && tutorialGroup.equals(((TutorialGroup) other).tutorialGroup)); // state check
    }

    @Override
    public int hashCode() {
        return tutorialGroup.hashCode();
    }

}
