package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

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
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
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
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Updates the tag in every contact.
     * @param oldTag The old tag to be changed.
     * @param newTag The new tag that is changed into.
     */
    public void changeRelevantPersonTag(Tag oldTag, Tag newTag) {
        for (int i = 0; i < internalList.size(); i++) {
            Person oldPerson = internalList.get(i);
            List<Tag> personTags = new ArrayList<>(oldPerson.getTags());
            if (personTags.contains(oldTag)) {
                int index = personTags.indexOf(oldTag);
                personTags.set(index, newTag);
                Set<Tag> newTags = new HashSet<>(personTags);
                Person newPerson = new Person(oldPerson.getName(),
                        oldPerson.getPhone(),
                        oldPerson.getEmail(),
                        oldPerson.getAddress(),
                        newTags);
                internalList.set(i, newPerson);
            }
        }
    }

    /**
     * Sorts the list by name, alphabetically.
     */
    public void sortByName() {
        SortedList<Person> sorted = internalList.sorted((curr, next) -> curr.getName().compareTo(next.getName()));
        internalList.setAll(sorted);
    }

    /**
     * Sorts the list by phone number, in increasing order.
     */
    public void sortByPhone() {
        SortedList<Person> sorted = internalList.sorted((curr, next) -> curr.getPhone().compareTo(next.getPhone()));
        internalList.setAll(sorted);
    }

    /**
     * Sorts the list by email, alphabetically.
     */
    public void sortByEmail() {
        SortedList<Person> sorted = internalList.sorted((curr, next) -> curr.getEmail().compareTo(next.getEmail()));
        internalList.setAll(sorted);
    }

    /**
     * Sorts the list by address, alphabetically.
     */
    public void sortByAddress() {
        SortedList<Person> sorted = internalList.sorted((curr, next) -> curr.getAddress().compareTo(next.getAddress()));
        internalList.setAll(sorted);
    }

    /**
     * Sorts the list in reverse order.
     */
    public void reverseSort() {
        FXCollections.reverse(internalList);
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

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
