package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) to ensure that the person with exactly the same fields will be removed.
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
     * Sorts the address book by name in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    public void sortByName(Boolean isReverse) {
        SortedList<Person> sorted;
        if (!isReverse) {
            sorted = internalList.sorted((curr, next) -> curr.getName().compareTo(next.getName()));
        } else {
            sorted = internalList.sorted((curr, next) -> -1 * (curr.getName().compareTo(next.getName())));
        }
        internalList.setAll(sorted);
    }

    /**
     * Sorts the address book by phone number in increasing order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    public void sortByPhone(Boolean isReverse) {
        SortedList<Person> sorted;
        if (!isReverse) {
            sorted = internalList.sorted((curr, next) -> curr.getPhone().compareTo(next.getPhone()));
        } else {
            sorted = internalList.sorted((curr, next) -> -1 * (curr.getPhone().compareTo(next.getPhone())));
        }
        internalList.setAll(sorted);
    }

    /**
     * Sorts the address book by email in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    public void sortByEmail(Boolean isReverse) {
        SortedList<Person> sorted;
        if (!isReverse) {
            sorted = internalList.sorted((curr, next) -> curr.getEmail().compareTo(next.getEmail()));
        } else {
            sorted = internalList.sorted((curr, next) -> -1 * (curr.getEmail().compareTo(next.getEmail())));
        }
        internalList.setAll(sorted);
    }

    /**
     * Sorts the address book by address in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order
     */
    public void sortByAddress(Boolean isReverse) {
        SortedList<Person> sorted;
        if (!isReverse) {
            sorted = internalList.sorted((curr, next) -> curr.getAddress().compareTo(next.getAddress()));
        } else {
            sorted = internalList.sorted((curr, next) -> -1 * (curr.getAddress().compareTo(next.getAddress())));
        }
        internalList.setAll(sorted);
    }

    /**
     * Sorts the address book by a tag.
     * Contacts with the tag appear before those without the tag.
     *
     * @param tag       The tag to sort with
     * @param isReverse Whether the sorting should be in reverse order
     */
    public void sortByTag(Tag tag, Boolean isReverse) {
        SortedList<Person> sorted;
        int flipIfReversed = isReverse ? -1 : 1;

        sorted = internalList.sorted((curr, next) -> {
            boolean currContains = curr.getTags().contains(tag);
            boolean nextContains = next.getTags().contains(tag);
            if (currContains && !nextContains) {
                return -1 * flipIfReversed;
            } else if (!currContains && nextContains) {
                return 1 * flipIfReversed;
            } else {
                return 0;
            }
        });
        internalList.setAll(sorted);
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
