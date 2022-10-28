package seedu.address.model;

import java.util.Comparator;

import javafx.collections.ObservableList;
import seedu.address.model.student.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTeachersPet {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the schedule list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getScheduleList();

    void sortPersons(Comparator<Person> comparator);
}
