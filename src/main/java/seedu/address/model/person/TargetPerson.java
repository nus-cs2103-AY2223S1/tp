package seedu.address.model.person;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents a Target Person when the application is running.
 */
public class TargetPerson {
    private Optional<Person> targetPerson;
    private final ObservableList<Person> targetPersonList = FXCollections.observableArrayList();

    public TargetPerson() {
        targetPerson = Optional.empty();
    }

    /** Returns an unmodifiable view of the list of target person */
    public ObservableList<Person> getAsObservableList() {
        return targetPersonList;
    }

    /**
     * Set the given person as target.
     * The person must exist in the address book.
     */
    public void set(Person person) {
        targetPerson = Optional.of(person);
        targetPersonList.clear();
        targetPersonList.add(person);
    }

    /** Returns the target {@code Person} */
    public Person get() {
        return targetPerson.orElseThrow(PersonNotFoundException::new);
    }

    /**
     * Set target to none.
     */
    public void clear() {
        targetPerson = Optional.empty();
        targetPersonList.clear();
    }

    /** Returns {@code true} if person is target person, {@code false} otherwise */
    public boolean isSamePerson(Person person) {
        return targetPerson.equals(Optional.of(person));
    }

    /** Returns {@code true} if target person is present, {@code false} otherwise */
    public boolean isPresent() {
        return targetPerson.isPresent();
    }

}
