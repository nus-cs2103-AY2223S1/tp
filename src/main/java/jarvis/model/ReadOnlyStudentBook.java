package jarvis.model;

import jarvis.model.student.Student;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyStudentBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();

}
