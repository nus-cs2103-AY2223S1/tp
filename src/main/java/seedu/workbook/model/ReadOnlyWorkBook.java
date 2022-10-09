package seedu.workbook.model;

import javafx.collections.ObservableList;
import seedu.workbook.model.person.Person;

/**
 * Unmodifiable view of an work book
 */
public interface ReadOnlyWorkBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
