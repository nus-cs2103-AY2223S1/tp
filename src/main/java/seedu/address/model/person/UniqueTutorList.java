package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.PersonTypeException;

/**
 * A list of tutors that enforces uniqueness between its elements and does not allow nulls.
 * A tutor is a student that has modules under teachingAssistantInfo.
 * A tutor is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the tutor being added or updated is
 * unique in terms of identity in the UniqueTutorList. However, the removal of a tutor uses Person#equals(Object) so
 * as to ensure that the tutor with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueTutorList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        if (toAdd.isTeachingAssistant()) {
            internalList.add(toAdd);
        } else {
            throw new PersonTypeException();
        }
    }

    /**
     * Replaces the tutor {@code target} in the list with {@code editedTutor}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedTutor} must not be the same as another existing tutor in the list.
     */
    public void setTutor(Person target, Student editedTutor) {
        requireAllNonNull(target, editedTutor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedTutor) && contains(editedTutor)) {
            throw new DuplicatePersonException();
        }

        if (editedTutor.isTeachingAssistant()) {
            internalList.set(index, editedTutor);
        } else {
            throw new PersonTypeException();
        }
    }

    /**
     * Removes the equivalent tutor from the list.
     * The tutor must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setTutors(UniqueTutorList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tutors}.
     * {@code tutors} must not contain duplicate tutors.
     */
    public void setTutors(List<Student> tutors) {
        requireAllNonNull(tutors);
        if (!tutorsAreUnique(tutors)) {
            throw new DuplicatePersonException();
        }
        if (listContainsTutors(tutors)) {
            internalList.setAll(tutors);
        } else {
            throw new PersonTypeException();
        }
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
                || (other instanceof UniqueTutorList // instanceof handles nulls
                && internalList.equals(((UniqueTutorList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean tutorsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSamePerson(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code students} contains only tutors.
     */
    private boolean listContainsTutors(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            if (!students.get(i).isTeachingAssistant()) {
                return false;
            }
        }
        return true;
    }
}
