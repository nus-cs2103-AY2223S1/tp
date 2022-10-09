package seedu.address.model.person.tutor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of tutors that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueTutorList extends UniquePersonList {
    private final ObservableList<Tutor> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tutor> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    public void setTutors(List<Tutor> tutors) {
        requireAllNonNull(tutors);
        if (!tutorsAreUnique(tutors)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(tutors);
    }

    public ObservableList<Tutor> asUnmodifiableObservableTutorList() {
        return internalUnmodifiableList;
    }

    private boolean tutorsAreUnique(List<Tutor> tutors) {
        for (int i = 0; i < tutors.size() - 1; i++) {
            for (int j = i + 1; j < tutors.size(); j++) {
                if (tutors.get(i).isSamePerson(tutors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
