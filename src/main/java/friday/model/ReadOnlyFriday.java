package friday.model;

import friday.model.student.Student;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of FRIDAY.
 */
public interface ReadOnlyFriday {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getPersonList();

}
