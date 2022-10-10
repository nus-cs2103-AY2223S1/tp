package seedu.address.model.person.tutor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of tutors that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueTutorList extends UniquePersonList {

    public void setTutors(List<Tutor> tutors) {
        requireAllNonNull(tutors);
        if (!tutorsAreUnique(tutors)) {
            throw new DuplicatePersonException();
        }

        this.internalList.setAll(tutors);
    }

    public ObservableList<Tutor> asUnmodifiableObservableTutorList() {
        ObservableList<Tutor> castToTutorList = FXCollections.observableArrayList();
        for (Person p : internalList) {
            Tutor t = (Tutor) p;
            castToTutorList.add(t);
        }
        return FXCollections.unmodifiableObservableList(castToTutorList);
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
