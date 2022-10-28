package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.DuplicatePersonException;
import seedu.address.model.student.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSamePerson(Student)}. As such, adding and updating of
 * persons uses Student#isSamePerson(Student) for equality so as to ensure that the student being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a student uses Student#equals(Object) so
 * as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSamePerson(Student)
 */
public class UniqueScheduleList implements Iterable<Student> {
    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalListRef = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalListRef);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
        updateInternalListRef();
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedStudent) && contains(editedStudent)) {
            throw new DuplicatePersonException();
        }
        internalList.set(index, editedStudent);
        updateInternalListRef();
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
        updateInternalListRef();
    }

    public void setPersons(UniqueScheduleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        updateInternalListRef();
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setPersons(List<Student> students) {
        requireAllNonNull(students);
        if (!personsAreUnique(students)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(students);
        updateInternalListRef();
    }

    /**
     *  Updates the {@code List<Student>} to ensure they have the correct display class date
     *  and returns a {@code List<Student>} which contains the filtered student list.
     */
    private List<Student> getScheduleList() {

        internalList.stream()
                .forEach(person -> person.updateDisplayClass(LocalDate.now()));
        return internalList
                .stream()
                .filter(person -> LocalDate.now().equals(person.getDisplayedClass().date))
                .sorted(Student::compareToByClassAsc)
                .collect(Collectors.toList());
    }

    /**
     * Update internalListRef
     */
    private void updateInternalListRef() {
        List<Student> list = getScheduleList();
        internalListRef.setAll(FXCollections.observableList(list));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        updateInternalListRef();
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueScheduleList // instanceof handles nulls
                        && internalList.equals(((UniqueScheduleList) other).internalList));
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
                if (students.get(i).isSamePerson(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
