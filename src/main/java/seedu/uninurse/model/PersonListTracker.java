package seedu.uninurse.model;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.model.person.Patient;

/**
 * Represents the persons added and deleted in an undoable command.
 */
public class PersonListTracker {
    /**
     * List of persons that were added to reach the current snapshot.
     */
    private final Optional<List<Patient>> addedPersons;

    /**
     * List of persons that were deleted to reach the current snapshot.
     */
    private final Optional<List<Patient>> deletedPersons;

    /**
     * Creates a PatientListTracker with no persons.
     */
    public PersonListTracker() {
        this.addedPersons = Optional.empty();
        this.deletedPersons = Optional.empty();
    }

    /**
     * Creates a PatientListTracker using addedPersons and deletedPersons.
     */
    public PersonListTracker(Optional<List<Patient>> addedPersons, Optional<List<Patient>> deletedPersons) {
        this.addedPersons = addedPersons;
        this.deletedPersons = deletedPersons;
    }

    public Optional<List<Patient>> getAddedPersons() {
        return this.addedPersons;
    }

    public Optional<List<Patient>> getDeletedPersons() {
        return this.deletedPersons;
    }

    public boolean isSinglePerson() {
        return addedPersons.map(list -> (list.size() <= 1)).orElse(false)
                || deletedPersons.map(list -> (list.size() <= 1)).orElse(false);
    }

    public boolean isMultiplePersons() {
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
        PersonListTracker tracker = (PersonListTracker) other;
        return this.addedPersons.equals(tracker.addedPersons)
                && this.deletedPersons.equals(tracker.deletedPersons);
    }
}



