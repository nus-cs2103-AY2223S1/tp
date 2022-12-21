package seedu.masslinkers.model;

import javafx.collections.ObservableList;
import seedu.masslinkers.model.student.Student;

//@@author
/**
 * Unmodifiable view of an mass linkers
 */
public interface ReadOnlyMassLinkers {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
