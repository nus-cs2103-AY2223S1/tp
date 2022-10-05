package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueAppointmentList implements Iterable<Appointment>{
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException(); // TODO
        }
        internalList.add(toAdd);
    }



    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(appointments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentList // instanceof handles nulls
                && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code appointments} contains only unique appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).equals(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
