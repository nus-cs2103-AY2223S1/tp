package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getPersonList();

    /**
     * Returns an unmodifiable view of the tutorials list.
     * This list will not contain any duplicate tutorials.
     */
    ObservableList<Tutorial> getTutorialList();

    /**
     * Returns an unmodifiable view of the consultations list.
     * This list will not contain any duplicate consultations.
     */
    ObservableList<Consultation> getConsultationList();
}
