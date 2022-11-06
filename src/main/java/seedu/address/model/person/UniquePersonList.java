package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     * @param toCheck person to be checked.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the internal list.
     * The person must not already exist in the list.
     * @param toAdd Person to be added to the internal list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     * @param target person to be replaced.
     * @param editedPerson person to replace with.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the internal list.
     * The person must exist in the list.
     * @param toRemove Person to be removed.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replace the contents of this list with {@code replacementPersons}.
     * @param replacementPersons UniquePersonList to replace the current internal list.
     */
    public void setPersons(UniquePersonList replacementPersons) {
        requireNonNull(replacementPersons);
        internalList.setAll(replacementPersons.internalList);
    }

    /**
     * Replaces the contents of this list with {@code replacementPersons}.
     * {@code replacementPersons} List to replace the current internal list, it must not contain duplicate persons.
     */
    public void setPersons(List<Person> replacementPersons) {
        requireAllNonNull(replacementPersons);
        if (!personsAreUnique(replacementPersons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(replacementPersons);
    }
    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        Integer personListSize = persons.size();
        for (int i = 0; i < personListSize - 1; i++) {
            for (int j = i + 1; j < personListSize; j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the list in-place using the specified {@code sortField}.
     *
     * @param sortField field to sort by.
     */
    public void sort(PersonSortField sortField) {
        requireNonNull(sortField);
        internalList.sort(sortField.getComparator());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
