package friday.model;

import friday.model.student.Student;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of FRIDAY.
 */
public interface ReadOnlyFriday {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
