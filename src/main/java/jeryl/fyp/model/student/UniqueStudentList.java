package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.student.exceptions.DuplicateStudentException;
import jeryl.fyp.model.student.exceptions.StudentNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}. As such, adding and
 * updating of students uses Student#isSameStudent(Student) for equality so as to ensure that the student being added
 * or updated is unique in terms of identity in the UniqueStudentList. However, the removal of a student uses
 * Student#equals(Object) so as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudentId(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final String MESSAGE_STUDENT_NOT_EXIST = "This student specified is not in the student list currently, "
            + "Please retry with another student ID. ";
    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudentId);
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
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameStudentId(editedStudent) && contains(editedStudent)) {
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
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);
    }

    /**
     * Filters the student list based on {@code studentPredicate}.
     */
    public ObservableList<Student> filter(Predicate<Student> studentPredicate) {
        return internalList.filtered(studentPredicate);
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
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudentId(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns unique Student if {@code students} contains the student with the specified studentId.
     */
    public Student getStudentByStudentId(StudentId studentId) throws StudentNotFoundException {
        Student student = null;
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getStudentId().equals(studentId)) {
                student = internalList.get(i);
            }
        }
        if (student == null) {
            throw new StudentNotFoundException(MESSAGE_STUDENT_NOT_EXIST);
        } else {
            return student;
        }
    }

    public Index getIndexByStudentId(StudentId studentId) {
        int index = internalList.size();
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getStudentId().equals(studentId)) {
                index = i;
            }
        }
        return new Index(index);
    }

    /**
     * Sorts our student list by project name (which naturally sorts it by alphabetical order as well)
     */
    public ObservableList<Student> sortByProjectName() {
        return internalList.sorted((Student a, Student b) -> a.getProjectName().toString().toLowerCase()
                .compareTo(b.getProjectName().toString().toLowerCase()));
    }

    /**
     * Sorts our student list by project Status(YTS, IP then DONE) then by alphabetical order
     */
    public ObservableList<Student> sortByProjectStatus() {
        return internalList.sorted(new Comparator<Student>() {

            public int compare(Student a, Student b) {
                int statusComp = b.getProjectStatus().toString().toLowerCase()
                        .compareTo(a.getProjectStatus().toString().toLowerCase());

                if (statusComp != 0) {
                    return statusComp;
                }

                return a.getProjectName().toString().toLowerCase()
                        .compareTo(b.getProjectName().toString().toLowerCase());
            }
        });
    }
}
