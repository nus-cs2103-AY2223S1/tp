package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.unmodifiableObservableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of uids of persons.
 * */
public class UidList implements Iterable<Uid> {
    private final ObservableList<Uid> internalList = observableArrayList();
    private final ObservableList<Uid> internalUnmodifiableList =
            unmodifiableObservableList(internalList);
    private String personNames = "";
    /**
     * Returns true if the uid to be checked is already present in the uid list.
     * @param toCheck uid to be checked if a duplicate is present in the list.
     */
    public boolean contains(Uid toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameUid);
    }
    /**
     * Returns the size of the uid list.
     */
    public int size() {
        return internalList.size();
    }
    /**
     * Adds a new uid to the list of uids it stores in its list.
     *
     * @param toAdd uid to be added to the list.
     */
    public void add(Uid toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
            // this operation will result in Duplicates Persons.
        }
        internalList.add(toAdd);
    }
    /**
     * Removes the person associated with the given {@code Uid toRemove} from the list.
     * The person must exist in the list.
     */
    public void remove(Uid toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Returns the internal list as an observableList object.
     */
    public ObservableList<Uid> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns a list of Person object corresponding to the uids in the UidList object.
     * @return a list of persons.
     */
    public ObservableList<Person> getPersons(Model model) {
        ObservableList<Person> persons = FXCollections.observableArrayList();
        ObservableList<Person> modelPersons = model.getFilteredPersonList();
        for (Person person : modelPersons) {
            if (this.contains(person.getUid())) {
                persons.add(person);
            }
        }
        return persons;
    }
    /**
     * Update personNames corresponding to the UidList with new string of person names.
     * This method is called after editPerson, tagEvent, untagEvent commands.
     * @return dummy return value true.
     */
    public boolean setPersonNames(Model model) {
        List<String> personNames = new ArrayList<>();
        ObservableList<Person> persons = model.getFilteredPersonList();
        int count = 0;
        for (Person person : persons) {
            if (this.contains(person.getUid())) {
                personNames.add(String.format("%s", person.getName().toString()));
                count += 1;
            }
        }
        if (count == 0) {
            this.personNames = "0 attendees";
        } else if (count == 1) {
            this.personNames = String.format("1 attendee: %s", personNames.get(0));
        } else {
            this.personNames = String.format("%d attendees: %s", count, String.join(", ", personNames));
        }
        return true;
    }
    public String getPersonNames() {
        return this.personNames;
    }
    @Override
    public Iterator<Uid> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UidList // instanceof handles nulls
                && internalList.equals(((UidList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
