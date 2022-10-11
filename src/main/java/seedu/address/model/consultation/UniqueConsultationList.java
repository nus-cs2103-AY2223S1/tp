package seedu.address.model.consultation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.UniqueConsultationList;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueConsultationList implements Iterable<Consultation>{
    private final ObservableList<Consultation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Consultation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Consultation as the given argument.
     */
    public boolean contains(Consultation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameConsultation);
    }

    /**
     * Returns true if the list contains a clashing Consultation as the given argument.
     */
    public boolean containsClashingWith(Consultation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isClashConsultation);
    }

    /**
     * Adds a Consultation to the list.
     * The Consultation must not already exist in the list.
     */
    public void add(Consultation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateConsultationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the contents of this list with {@code Consultations}.
     * {@code Consultations} must not contain duplicate Consultations.
     */
    public void setConsultations(List<Consultation > Consultations) {
        requireAllNonNull(Consultations);
        if (!ConsultationsAreUnique(Consultations)) {
            throw new DuplicateConsultationException();
        }

        internalList.setAll(Consultations);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Consultation > asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Consultation > iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueConsultationList // instanceof handles nulls
                && internalList.equals(((UniqueConsultationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Consultations} contains only unique Consultations.
     */
    private boolean ConsultationsAreUnique(List<Consultation > Consultations) {
        for (int i = 0; i < Consultations.size() - 1; i++) {
            for (int j = i + 1; j < Consultations.size(); j++) {
                if (Consultations.get(i).isSameConsultation(Consultations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
