package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.COMPARATOR_UNGROUP_APPOINTMENTS;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import seedu.address.model.person.exceptions.AppointmentNotFoundException;
import seedu.address.model.person.exceptions.DuplicateAppointmentException;

/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * An appointment is considered unique by comparing using {@code Appointment#equals(Object)}.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final Callback<Appointment, Observable[]> extractor = Appointment::getProperties;

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList(extractor);
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private Comparator<Appointment> comparator = COMPARATOR_UNGROUP_APPOINTMENTS;

    /**
     * Constructs a UniqueAppointmentList with an added listener to sort list when appointments are updated.
     */
    public UniqueAppointmentList() {
        internalList.addListener((ListChangeListener<Appointment>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    sort(comparator);
                }
            }

        });
    }

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
    }



    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    /**
     * Removes all the equivalent appointments from the list.
     * The appointments must exist in the list.
     *
     * @param toRemove List of appointments to remove.
     */
    public void removeAppointments(List<Appointment> toRemove) {
        requireNonNull(toRemove);
        for (Appointment appointment : toRemove) {
            if (!internalList.remove(appointment)) {
                throw new AppointmentNotFoundException();
            }
        }
    }

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment of {@code editedAppointment} must not be the same as another existing appointment
     * in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.isSameAppointment(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
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

    /**
     * Sorts the contents of the appointment list with {@code comparator}.
     */
    public void sort(Comparator<Appointment> comparator) {
        this.comparator = comparator;
        internalList.sort(comparator);
    }
}
