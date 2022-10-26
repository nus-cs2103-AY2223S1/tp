package seedu.classify.model;

import javafx.collections.ObservableList;
import seedu.classify.model.student.Student;

/**
 * Unmodifiable view of a student record.
 */
public interface ReadOnlyStudentRecord {

    /**
     * Returns an unmodifiable view of the student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
