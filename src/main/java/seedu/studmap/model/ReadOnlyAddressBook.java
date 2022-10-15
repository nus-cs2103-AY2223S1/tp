package seedu.studmap.model;

import javafx.collections.ObservableList;
import seedu.studmap.model.student.Student;

/**
 * Unmodifiable view of an studmap book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
