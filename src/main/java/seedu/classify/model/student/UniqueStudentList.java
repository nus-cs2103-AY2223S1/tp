package seedu.classify.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.classify.commons.util.CollectionUtil;
import seedu.classify.model.student.exceptions.DuplicateStudentException;
import seedu.classify.model.student.exceptions.StudentNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}. As such, adding and
 * updating of students uses Student#isSameStudent(Student) for equality to ensure that the student being added or
 * updated is unique in terms of identity in the UniqueStudentList. However, the removal of a student uses
 * Student#equals(Object) to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#hasSameNameOrId(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasSameNameOrId);
    }

    /**
     * Returns true if the list contains an equivalent student as the given argument, where the list excludes
     * a particular student to be checked.
     * @param studentToExclude the student to be excluded from the check.
     * @param studentToCheck the student to be checked.
     * @return true if there is a matching student identity in the student record
     */
    public boolean excludesButContains(Student studentToExclude, Student studentToCheck) {
        return internalList.stream().filter(s -> !s.equals(studentToExclude)).anyMatch(studentToCheck::hasSameNameOrId);
    }

    public void sortList(Comparator<Student> studentComparator) {
        internalList.sort(studentComparator);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setPerson(Student target, Student editedStudent) {
        CollectionUtil.requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.hasSameNameOrId(editedStudent) && contains(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        CollectionUtil.requireAllNonNull(students);
        if (!personsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                        && internalList.equals(((UniqueStudentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean personsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).hasSameNameOrId(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
