package seedu.uninurse.model;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.model.person.Person;

/**
 * Represents the persons added and deleted in an undoable command.
 */
public class PersonListTracker {
    /**
     * List of persons that were added to reach the current snapshot.
     */
    private final Optional<List<Person>> addedPersons;

    /**
     * List of persons that were deleted to reach the current snapshot.
     */
    private final Optional<List<Person>> deletedPersons;

    /**
     * Creates a PersonListTracker with no persons.
     */
    public PersonListTracker() {
        this.addedPersons = Optional.empty();
        this.deletedPersons = Optional.empty();
    }

    /**
     * Creates a PersonListTracker using addedPersons and deletedPersons.
     */
    public PersonListTracker(Optional<List<Person>> addedPersons, Optional<List<Person>> deletedPersons) {
        this.addedPersons = addedPersons;
        this.deletedPersons = deletedPersons;
    }

    public Optional<List<Person>> getAddedPersons() {
        return this.addedPersons;
    }

    public Optional<List<Person>> getDeletedPersons() {
        return this.deletedPersons;
    }

    public boolean isSinglePerson() {
        return addedPersons.map(list -> (list.size() <= 1)).orElse(false)
                || deletedPersons.map(list -> (list.size() <= 1)).orElse(false);
    }

    public boolean isMultiplePerson() {
        return addedPersons.map(list -> (list.size() > 1)).orElse(false)
                || deletedPersons.map(list -> (list.size() > 1)).orElse(false);
    }

    public boolean isAdd() {
        return addedPersons.map(list -> (list.size() > 0)).orElse(false)
                && deletedPersons.map(list -> (list.size() == 0)).orElse(true);
    }

    public boolean isDelete() {
        return deletedPersons.map(list -> (list.size() > 0)).orElse(false)
                && addedPersons.map(list -> (list.size() == 0)).orElse(true);
    }

    public boolean isEdit() {
        return addedPersons.map(list -> (list.size() == 1)).orElse(false)
                && deletedPersons.map(list -> (list.size() == 1)).orElse(false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonListTracker)) {
            return false;
        }

        // state check
        PersonListTracker o = (PersonListTracker) other;
        return addedPersons.equals(o.addedPersons)
                && deletedPersons.equals(o.deletedPersons);
    }
}



