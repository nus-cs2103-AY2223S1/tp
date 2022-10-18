package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.student.Student;
import seedu.address.model.ta.TeachingAssistant;
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
     * Returns an unmodifiable view of the reminders list.
     * This list will not contain any duplicate reminders.
     */
    ObservableList<Reminder> getReminderList();

    /**
     * Returns an unmodifiable view of the tutorials list.
     * This list will not contain any duplicate tutorials.
     */
    ObservableList<Tutorial> getTutorialList();

    /**
     * Returns an unmodifiable view of the teaching assistant list.
     * This list will not contain any duplicate teaching assistants.
     */
    ObservableList<TeachingAssistant> getTeachingAssistantList();

    /**
     * Returns an unmodifiable view of the consultations list.
     * This list will not contain any duplicate consultations.
     */
    ObservableList<Consultation> getConsultationList();
}
