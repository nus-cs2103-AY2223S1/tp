package seedu.studmap.model;

import javafx.collections.ObservableList;
import seedu.studmap.model.student.Student;

/**
 * Unmodifiable view of a studMap
 */
public interface ReadOnlyStudMap {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
