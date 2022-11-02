package seedu.masslinkers.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.model.student.Telegram;
import seedu.masslinkers.model.student.UniqueStudentList;

//@@author
/**
 * Wraps all data at the mass linkers level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class MassLinkers implements ReadOnlyMassLinkers {
    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public MassLinkers() {}

    /**
     * Creates an MassLinkers using the Students in the {@code toBeCopied}
     */
    public MassLinkers(ReadOnlyMassLinkers toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Resets the existing data of this {@code MassLinkers} with {@code newData}.
     */

    public void resetData(ReadOnlyMassLinkers newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());

    }
    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    //// student-level operations
    /**
     * Returns true if a student with the same identity as {@code student} exists in the mass linkers.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if a student with Telegram handle that is same as {@code telegram} exists in
     * the mass linkers.
     * @param telegram the telegram
     * @return boolean indicating if such telegram is owned by anyone in the mass linkers.
     */
    public boolean hasTelegram(Telegram telegram) {
        requireNonNull(telegram);
        return students.containsTelegram(telegram);
    }

    /**
     * Returns true if a student with GitHub that is same as {@code gitHub} exists in
     * the mass linkers.
     * @param gitHub the gitHub
     * @return boolean indicating if such gitHub is owned by anyone in the mass linkers.
     */
    public boolean hasGitHub(GitHub gitHub) {
        requireNonNull(gitHub);
        return students.containsGitHub(gitHub);
    }

    /**
     * Returns true if a student with email that is same as {@code email} exists in
     * the mass linkers.
     * @param email the email
     * @return boolean indicating if such email is owned by anyone in the mass linkers.
     */
    public boolean hasEmail(Email email) {
        requireNonNull(email);
        return students.containsEmail(email);
    }

    /**
     * Returns true if a student with phone number that is same as {@code phone} exists in
     * the mass linkers.
     * @param phone the phone number
     * @return boolean indicating if such phone number is owned by anyone in the mass linkers.
     */
    public boolean hasPhone(Phone phone) {
        requireNonNull(phone);
        return students.containsPhone(phone);
    }

    /**
     * Adds a student to the mass linkers.
     * The student must not already exist in the mass linkers.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the mass linkers.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the mass linkers.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code MassLinkers}.
     * {@code key} must exist in the mass linkers.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods
    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size()
                + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MassLinkers // instanceof handles nulls
                && students.equals(((MassLinkers) other).students));
    }

}
